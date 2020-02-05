package io.github.glaz_egy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class OnlineList extends JFrame {

	private JPanel contentPane;
	private JList<String> list;
	private Reload reloader;
	public DefaultListModel<String> model;
	public String enemyAddr;
	public boolean isAccept = false;
	public ReversiMain par;
	public boolean isAttack = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlineList frame = new OnlineList(new ReversiMain());
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OnlineList(ReversiMain m) {
		setTitle("JRP3 - オンラインリスト");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo5.png"));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				par.sockEndCommand(reloader.port);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		par = m;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(10, 1, 0, 0));

		JButton attackButton = new JButton("対戦する");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enemyAddr = list.getSelectedValue();
				par.enemyAddr = enemyAddr;
				System.out.println(enemyAddr);
				par.sockEndCommand(Reversi.CLIENT_PORT);
				reloader.end = true;
				try {
					TimeUnit.MICROSECONDS.sleep(100);
				} catch (InterruptedException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				if(!isAttack){
					attack(enemyAddr);
					isAttack = true;
				}
			}
		});
		panel_1.add(attackButton);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		model = new DefaultListModel<String>();
		list = new JList<>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(list);
		reloader = new Reload(this);
	}

	public void start(){
		reloader.end = false;
		reloader.port = Reversi.CLIENT_PORT;
		(new Thread(reloader)).start();
		setVisible(true);
	}

	private void attack(String addr){
		reloader.port = Reversi.GAME_PORT;
		Reversi.datagramSend("attack:"+par.myAddr, addr, Reversi.SERVER_PORT, false);
		System.out.println("attack");
		reloader.end = false;
		(new Thread(reloader)).start();
		System.out.println(reloader.end);
	}


	static class Reload implements Runnable{

		private OnlineList par;
		public int port;
		public boolean end = false;

		public Reload(OnlineList p){
			par = p;
		}
		@Override
		public void run() {
			while(!end){
				if(port  == Reversi.CLIENT_PORT){
					try {
						TimeUnit.MICROSECONDS.sleep(100);
					} catch (InterruptedException e1) {
						// TODO 自動生成された catch ブロック
					}
				}
				String[] re = Reversi.datagramReceive(port);
				System.out.println("Connect from"+re[1]);
				String addr = re[1];
				String inputLine = re[0];
				String[] cmd = inputLine.split(":", 0);
				if(cmd[0].equals("accept") && cmd[1].equals(par.enemyAddr)){
					par.isAccept = true;
					par.par.onlineBattle = true;
					end = true;
					par.setVisible(false);
					par.par.onlineStart(false);
				}else if(cmd[0].equals("refuse") && cmd[1].equals(par.enemyAddr)){
					par.isAttack = false;
					end = false;
				}else if(cmd[0].equals("wait")){
					if(!par.model.contains(addr) && !par.par.myAddr.equals(addr)){
						par.model.addElement(addr);
					}
				}else if(cmd[0].equals("cmd") && cmd[1].equals("end")){
					end = true;
				}
				System.out.println(inputLine);
			}
			System.out.println("end");
		}
	}
}
