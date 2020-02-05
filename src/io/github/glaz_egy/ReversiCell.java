package io.github.glaz_egy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;

public class ReversiCell extends JButton implements Runnable, Serializable{
	public static final int STONE1 = 1;
	public static final int STONE2 = -1;
	public static final int EMPTY = 0;
	private int id, x, y;
	private String Te = "●";
	private boolean isPut = false;
	private boolean canPut = false;
	private int[][] NextData;
	private int[][] NowData;
	private int canReverse;
	private int MyColor;
	private int turnColor;
	private ReversiMain par;
	public ReversiCell(int ID, int[][] NowCellData, ReversiMain m) throws ClassNotFoundException, IOException{
		super();
		par = m;
		NowData = deepcopy(NowCellData);
		setBackground(par.board);
		setFont(new Font("メイリオ", Font.PLAIN, 85));
		setFocusPainted(false);
		setMargin(new Insets(0, 0, 0, 0));
		id = ID;
		turnColor = STONE1;
		x = id/10;
		y = id%10;
		MyColor = NowData[x][y];
		NextData = deepcopy(NowData);
		if(MyColor == 0) checkCanPut();
		else canPut = false;
		setColor(NowData[x][y] == 1 ? par.stone1 : par.stone2, NowData[x][y] != 0);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(MyColor == 0 && !(par.onlineBattle ^ par.isMyTurn))setColor((turnColor == STONE1 ? par.stone1 : par.stone2), false);
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(MyColor == 0 && !(par.onlineBattle ^ par.isMyTurn)) setColor((turnColor == STONE1 ? par.stone1 : par.stone2), true);
			}
		});
	}
	public int getXPos(){
		return x;
	}

	public int getYPos(){
		return y;
	}

	public void setStone(String s) {
		Te = s;
		if(isPut) setText(Te);
	}

	public int[][] Put(){
		NextData[x][y] = turnColor;
		return NextData;
	}

	public void update(int[][] data){
		try {
			NowData = deepcopy(data);
			NextData = deepcopy(NowData);
		} catch (ClassNotFoundException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		MyColor = NowData[x][y];
	}

	public void nextTurn(){
		turnColor = (turnColor == STONE1 ? STONE2:STONE1);
	}

	public void setTurn(int turn){
		turnColor = turn;
	}

	public void refresh(){
		try {
			setBackground(par.board);
			setColor((MyColor == STONE1 ? par.stone1 : par.stone2), MyColor != 0);
			if(MyColor == 0) checkCanPut();
			else canPut = false;
		} catch (ClassNotFoundException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void refresh(int[][] data){
		setColor((data[x][y] == STONE1 ? par.stone1 : par.stone2), data[x][y] != 0);
	}

	public void setNum(int num, boolean outFlag){
		if(outFlag){
			setForeground(Color.BLACK); setText(""+num);
		}else setText("");
	}

	public boolean isPut(){
		return (MyColor != 0);
	}

	public int MyColor(){
		return MyColor;
	}

	public boolean canPut(){
		return canPut;
	}

	public int getID() {
		return id;
	}

	public int canReverse(){
		return canReverse;
	}

	public String nowColor(){
		return (turnColor == STONE1 ? "STONE1":"STONE2");
	}

	public void setColor(Color color, boolean putFlag) {
		if(putFlag){
			setForeground(color);
			setText(Te);
		}else {
			setText("");
		}
		isPut = putFlag;
	}

	public void checkCanPut() throws ClassNotFoundException, IOException{
		canReverse = 0;
		boolean preCanFlag = false;
		boolean end = false;
		int tmp = 0;
		int[][] tmpData = deepcopy(NextData);
		canPut = false;
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				preCanFlag = false;
				end = false;
				tmp = 0;
				if(i==0 && j==0) continue;
				for(int[] a = {x+i, y+j}; a[0] >= 0 && a[1] >= 0 && a[0] <= 7 && a[1] <= 7; a[0]+=i, a[1]+=j){
					if(NowData[a[0]][a[1]] != turnColor && NowData[a[0]][a[1]] != 0){
						preCanFlag = true;
						tmp++;
						tmpData[a[0]][a[1]] = turnColor;
					}else if(NowData[a[0]][a[1]] == turnColor){
						if(preCanFlag){
							canReverse += tmp;
							NextData = deepcopy(tmpData);
							canPut = true;
							end = true;
						}else tmpData = deepcopy(NextData);
						break;
					}else if(NowData[a[0]][a[1]] == 0){
						tmpData = deepcopy(NextData);
						break;
					}
				}
				if(!end) tmpData = deepcopy(NextData);
			}
		}

	}

	@SuppressWarnings("unchecked")
    public static  <T> T deepcopy(T obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(obj);
        return (T) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
    }

	@Override
	public void run() {
		refresh();
	}
}
