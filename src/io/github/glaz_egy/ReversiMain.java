package io.github.glaz_egy;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class ReversiMain {
	ReversiViewer frame;
	ReversiCell[][] cell;
	ReversiSetting settingWindow;
	private int NowCellData[][];
	private int turnLog[][][];
	private int turn = 0, maxTurn = 0;
	private boolean canPut = false, endFlag = false;
	private boolean botEnable = false;
	public boolean isMyTurn = false;
	public BotTemplate bot;
	private ReversiPluginSelect pluginSelect;
	public String userName;
	public LANGameSocketControl sock;
	public AttackDialog dialog;
	public boolean onlineBattle = false;
	public OnlineList onlineList;
	public boolean isWait = false;
	public String myAddr;
	public String enemyAddr;
	public Setting4Reversi setting;
	public ColorTheme theme;
	public Color stone1, stone2;
	public Color board;

	public void initCellData(){
		NowCellData = new int[8][8];
		turnLog = new int[128][8][8];
		turn = 0;
		maxTurn = 0;
		endFlag = false;
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				if ((x == 3 && y == 3)||(x == 4 && y == 4)) NowCellData[x][y] = ReversiCell.STONE2;
				else if ((x == 3 && y == 4)||(x == 4 && y == 3)) NowCellData[x][y] = ReversiCell.STONE1;
				else NowCellData[x][y] = ReversiCell.EMPTY;
			}
		}
		turnLog[turn] = NowCellData.clone();
		frame.turnColorBox.setText("Now is the "+"STONE1"+" turn");
	}

	public void initBoard() {
		cell = new ReversiCell[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				try {
					cell[j][i] = new ReversiCell(j*10+i, NowCellData, this);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				cell[j][i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!(onlineBattle ^ isMyTurn)) move((ReversiCell)e.getSource());
					}
				});
				cell[j][i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseExited(MouseEvent e) {
						if(((ReversiCell)e.getSource()).canPut())
							((ReversiCell)e.getSource()).setNum(((ReversiCell)e.getSource()).canReverse(), frame.getStoneNum.isSelected());
					}
				});
			}
		}
		canPut = true;
		frame.setBoard(cell);
	}

	public static void main(String[] args) {
		(new ReversiMain()).run();
	}

	private void Passed(){
		frame.logOut("turn:"+(maxTurn+1)+(onlineBattle ? (isMyTurn ? " user:"+myAddr: " user:"+enemyAddr):(" Color:"+cell[0][0].nowColor()))+" Passed");
		if(onlineBattle && isMyTurn){
			String str = "pass:pass;ip:"+myAddr;
			Reversi.datagramSend(str, enemyAddr, Reversi.GAME_PORT, false);
		}
		turn += 1;
		maxTurn += 1;
		for(int y = 0; y<8; y++){
			for(int x = 0; x < 8; x++){
				cell[x][y].nextTurn();
				cell[x][y].update(NowCellData);
				cell[x][y].refresh();
				if(cell[x][y].canPut()) cell[x][y].setNum(cell[x][y].canReverse(), frame.getStoneNum.isSelected());
			}
		}
		if(onlineBattle && isMyTurn){
			isMyTurn = false;
			(new Thread(new WaitingGame(this))).start();
		}
	}

	private void endCheck(){
		int i = 0;
		int b = 0, w = 0;
		for(int y = 0; y<8; y++){
			for(int x = 0; x < 8; x++){
				if(cell[x][y].isPut()) i++;
				if(cell[x][y].MyColor() == 1) b += 1;
				else if(cell[x][y].MyColor() == -1) w += 1;
			}
		}
		if(i == 64){
			frame.logOut("End of game."+" STONE1: "+(b)+" STONE2: "+(w)+"\n"+(b==w?"DRAW!!":(b>w?"STONE1 WIN!!":"STONE2 WIN!!")));
			endFlag = true;
		}
	}

	private void _move(ReversiCell tmp_cell){
		canPut = false;
		frame.logOut("turn:"+(maxTurn+1)+" Pos:"+(tmp_cell.getID()/10+1)+", "+(tmp_cell.getID()%10+1)+(onlineBattle ? (isMyTurn ? " user:"+myAddr: " user:"+enemyAddr):(" Color:"+tmp_cell.nowColor())));
		if(onlineBattle && isMyTurn){
			String str = "put:"+tmp_cell.getXPos()+","+tmp_cell.getYPos()+";ip:"+myAddr;
			Reversi.datagramSend(str, enemyAddr, Reversi.GAME_PORT, false);
		}
		turn += 1;
		maxTurn += 1;
		NowCellData = tmp_cell.Put().clone();
		turnLog[turn] = NowCellData.clone();
		frame.turnColorBox.setText("Now is the "+(tmp_cell.nowColor() == "STONE1"?"STONE2":"STONE1")+" turn");
		for(int y = 0; y<8; y++){
			for(int x = 0; x < 8; x++){
				cell[x][y].nextTurn();
				cell[x][y].update(NowCellData);
				cell[x][y].refresh();
				if(!canPut) canPut = cell[x][y].canPut();
				if(cell[x][y].canPut()) cell[x][y].setNum(cell[x][y].canReverse(), frame.getStoneNum.isSelected());
			}
		}
		endCheck();
		if(!canPut && !endFlag && !onlineBattle)Passed();
		if(!endFlag && onlineBattle && isMyTurn){
			isMyTurn = false;
			(new Thread(new WaitingGame(this))).start();
		}

	}

	private void botMove(){
		//bot function
		//c = OthelloCell.deepcopy(cell);
		ReversiCell next = bot.nextPos(cell);
		if(next != null){
			_move(next);
		}
	}

	private void move(ReversiCell tmp_cell){
		if (tmp_cell.canPut()){
			_move(tmp_cell);
			if(canPut && botEnable && !endFlag && !onlineBattle){
				botMove();
			}
		}else{
			System.out.println("You can't put it");
		}
	}

	private void resetGame(){
		initCellData();
		for(int y = 0; y<8; y++){
			for(int x = 0; x < 8; x++){
				cell[x][y].setTurn(ReversiCell.STONE1);
				cell[x][y].update(NowCellData);
				cell[x][y].refresh();
				if(cell[x][y].canPut()) cell[x][y].setNum(cell[x][y].canReverse(), frame.getStoneNum.isSelected());
			}
		}
	}

	private void setting(){
		settingWindow = new ReversiSetting(this);
		settingWindow.setVisible(true);
	}

	public void resetting(){
		switch(setting.getTheme()){
		case 0:
			theme = new NormalTheme();
			theme.setMainColor(this);
			break;
		case 1:
			theme = new DarkTheme();
			theme.setMainColor(this);
			break;
		case 2:
			theme = new LightTheme();
			theme.setMainColor(this);
			break;
		case 3:
			theme = new SakuraTheme();
			theme.setMainColor(this);
			break;
		case 4:
			setting.setMainColor(this);
			break;
		default:
			break;
		}

		userName = setting.getUserName();
		for(int y = 0; y<8; y++){
			for(int x = 0; x < 8; x++){
				cell[x][y].refresh();
				if(cell[x][y].canPut()) cell[x][y].setNum(cell[x][y].canReverse(), frame.getStoneNum.isSelected());
			}
		}
	}

	public void botEnable(boolean b){
		botEnable = b;
	}

	public void onlineStart(boolean isBlack){
		isMyTurn = isBlack;
		System.out.println(isBlack);
		resetGame();
		if(!isBlack)
			(new Thread(new WaitingGame(this))).start();
	}

	public void onlineStatus(boolean status){
		onlineBattle = status;
		if(status) frame.status.setText("LANステータス：オンライン");
		else frame.status.setText("LANステータス：オフライン");
	}

	public void sockEndCommand(int port){
		Reversi.datagramSend("cmd:end", "127.0.0.1", port, false);
	}


	private void frameInit(){
		frame.reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.logOut("Reset GAME");
				resetGame();
			}
		});
		frame.newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.logOut("Reset GAME");
				resetGame();

			}
		});
		frame.setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setting();
			}
		});
		frame.botNothing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botEnable = false;
			}
		});
		frame.maxSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botEnable = true;
				bot = new MaxSelect();
			}
		});
		frame.botPlugin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				botEnable = true;
				pluginSelect.setVisible(true);
			}
		});
		frame.offlineStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(sock.isRunning)sock.endProcess();
				isWait = false;
				onlineStatus(false);
				isMyTurn = false;
			}
		});
		frame.onlineStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				isWait = true;
				onlineStatus(true);
				(new Thread(sock)).start();
			}
		});
		frame.attackStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(sock.isRunning)sock.endProcess();
				isWait = false;
				onlineList.start();
				onlineStatus(true);
			}
		});
		frame.about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				(new HelpWindow()).setVisible(true);
			}
		});
		frame.nextLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn += 1;
				if(maxTurn >= turn){
					for(int y = 0; y<8; y++){
						for(int x = 0; x < 8; x++){
							cell[x][y].refresh(turnLog[turn]);
						}
					}
				}else{
					turn = maxTurn;
				}
			}
		});
		frame.backLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn -= 1;
				if(turn >= 0){
					for(int y = 0; y<8; y++){
						for(int x = 0; x < 8; x++){
							cell[x][y].refresh(turnLog[turn]);
						}
					}
				}else{
					turn = 0;
				}
			}
		});
		frame.getStoneNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int y = 0; y<8; y++){
					for(int x = 0; x < 8; x++){
						if(cell[x][y].canPut()) cell[x][y].setNum(cell[x][y].canReverse(), frame.getStoneNum.isSelected());
					}
				}
			}
		});
	}

	private void run() {
		System.out.println(new File(".").getAbsoluteFile().getParent());
		dialog = new AttackDialog(this);
		onlineList = new OnlineList(this);
		pluginSelect = new ReversiPluginSelect(this);
		sock = new LANGameSocketControl(this);
		setting = new Setting4Reversi();
		setting = setting.loadSettings(Setting4Reversi.SETTING_FILE);
		frame = new ReversiViewer(setting);
		switch(setting.getTheme()){
		case 0:
			theme = new NormalTheme();
			theme.setMainColor(this);
			break;
		case 1:
			theme = new DarkTheme();
			theme.setMainColor(this);
			break;
		case 2:
			theme = new LightTheme();
			theme.setMainColor(this);
			break;
		case 3:
			theme = new SakuraTheme();
			theme.setMainColor(this);
			break;
		case 4:
			setting.setMainColor(this);
			break;
		default:
			break;
		}
		userName = setting.getUserName();
		initCellData();
		initBoard();

		frameInit();
		frame.setVisible(true);
	}

	static class WaitingGame implements Runnable{
		public ReversiMain par;
		public WaitingGame(ReversiMain m){
			par = m;
		}
		private void waitGame(){
			String[][] cmd = new String[10][2];
			int j = 0;
			while(!par.isMyTurn){
				String[] re = Reversi.datagramReceive(Reversi.GAME_PORT);
				System.out.println("Connect from"+re[1]);
				String inputLine = re[0];
				for(String i:inputLine.split(";", 0)){
					cmd[j] = i.split(":", 0);
					j++;
				}
				if(cmd[0][0].equals("put") && cmd[1][1].equals(par.enemyAddr)){
					String[] pos = cmd[0][1].split(",", 0);
					par._move(par.cell[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])]);
					par.isMyTurn = true;
					if(!par.canPut && !par.endFlag) par.Passed();
				}else if(cmd[0][0].equals("pass") && cmd[1][1].equals(par.enemyAddr)){
					par.Passed();
					par.isMyTurn = true;
				}
				System.out.println(inputLine);
			}
		}
		@Override
		public void run() {
			waitGame();
		}
	}


}
