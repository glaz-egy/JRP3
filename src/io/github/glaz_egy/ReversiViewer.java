package io.github.glaz_egy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class ReversiViewer extends JFrame {

	private JPanel contentPane;
	public JPanel mainBoard;
	private ReversiCell[][] cell;
	public JPanel backPanel, MainPanel, panel_3, StatusBar;
	public JButton reset, backLog, nextLog;
	public JTextArea logArea;
	public JTextField turnColorBox;
	public JMenuItem newGame;
	public JCheckBoxMenuItem getStoneNum;
	public JMenuItem setting;
	public JRadioButtonMenuItem maxSelect, botNothing, botPlugin, Star, Stone;
	public JRadioButtonMenuItem offlineStatus, onlineStatus, groupIndication, attackStart;
	public JTextField status;
	public JMenuBar menuBar;
	public JMenu editMenu, fileMenu, stoneShapeMenu, helpMenu, botSelectMenu, LANstatus;
	private JMenu mnNewMenu;
	public JMenuItem about;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReversiViewer frame = new ReversiViewer(new Setting4Reversi());
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
	public ReversiViewer(Setting4Reversi set) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo5.png"));
		setTitle("JRP3 - メイン画面");
		Font f = null;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File(set.getFontFile()));
			f = f.deriveFont(16.0f);
			UIManager.put("Button.font", f);
			UIManager.put("Label.font", f);
			UIManager.put("TextArea.font", f);
			UIManager.put("TextField.font", f);
			UIManager.put("Menu.font", f);
			UIManager.put("MenuItem.font", f);
			UIManager.put("CheckBoxMenuItem.font", f);
			UIManager.put("RadioButtonMenuItem.font", f);
			UIManager.put("TextField.font", f);
			UIManager.put("TextArea.font", f);
		} catch (FontFormatException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 900);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu("ファイル");
		menuBar.add(fileMenu);

		newGame = new JMenuItem("新規ゲーム");
		fileMenu.add(newGame);

		setting = new JMenuItem("設定");
		fileMenu.add(setting);

		editMenu = new JMenu("編集");
		menuBar.add(editMenu);

		Stone = new JRadioButtonMenuItem("丸");
		Stone.setSelected(true);
		Star = new JRadioButtonMenuItem("星");

		ButtonGroup stoneShape = new ButtonGroup();

		stoneShape.add(Star);
		stoneShape.add(Stone);

		editMenu.add(Stone);
		editMenu.add(Star);


		editMenu.addSeparator();

		getStoneNum = new JCheckBoxMenuItem("取得可能石数表示");
		editMenu.add(getStoneNum);

		editMenu.addSeparator();

		botNothing = new JRadioButtonMenuItem("なし");
		botNothing.setSelected(true);

		maxSelect = new JRadioButtonMenuItem("最大取得位置選択");

		ButtonGroup botSelect = new ButtonGroup();

		botSelect.add(botNothing);
		botSelect.add(maxSelect);


		botPlugin = new JRadioButtonMenuItem("プラグイン");

		botSelect.add(botNothing);
		botSelect.add(maxSelect);
		botSelect.add(botPlugin);

		editMenu.add(botNothing);
		editMenu.add(maxSelect);
		editMenu.add(botPlugin);

		editMenu.addSeparator();

		offlineStatus = new JRadioButtonMenuItem("オフライン");
		offlineStatus.setSelected(true);

		onlineStatus = new JRadioButtonMenuItem("オンライン");

		groupIndication = new JRadioButtonMenuItem("グループ一覧");
		attackStart = new JRadioButtonMenuItem("対戦を仕掛ける");


		ButtonGroup LANStatus = new ButtonGroup();

		LANStatus.add(offlineStatus);
		LANStatus.add(onlineStatus);
		LANStatus.add(groupIndication);
		LANStatus.add(attackStart);

		editMenu.add(offlineStatus);
		editMenu.add(onlineStatus);
		editMenu.add(groupIndication);
		editMenu.add(attackStart);

		mnNewMenu = new JMenu("ヘルプ");
		menuBar.add(mnNewMenu);

		about = new JMenuItem("JRP3について");
		mnNewMenu.add(about);



		Stone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						cell[j][i].setStone("●");
					}
				}
			}
		});
		Star.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						cell[j][i].setStone("★");
					}
				}
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		MainPanel = new JPanel();
		contentPane.add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		MainPanel.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));

		turnColorBox = new JTextField();
		turnColorBox.setFont(f.deriveFont(18.0f));
		turnColorBox.setEditable(false);
		panel_4.add(turnColorBox);
		turnColorBox.setColumns(10);


		JPanel panel_2 = new JPanel();
		MainPanel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		backLog = new JButton("←");
		panel_3.add(backLog);
		nextLog = new JButton("→");
		panel_3.add(nextLog);

		JPanel panel_1 = new JPanel();
		MainPanel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));

		backPanel = new JPanel();
		panel_1.add(backPanel, BorderLayout.WEST);

		reset = new JButton("リセット");
		backPanel.add(reset);

		JScrollPane scrollPane = new JScrollPane();

		logArea = new JTextArea();
		logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
		logArea.setEditable(false);
		logArea.setColumns(50);

		scrollPane.setViewportView(logArea);

		panel_1.add(scrollPane, BorderLayout.EAST);

		mainBoard = new JPanel();
		MainPanel.add(mainBoard, BorderLayout.CENTER);
		mainBoard.setLayout(new GridLayout(8, 8, 0, 0));

		StatusBar = new JPanel();
		contentPane.add(StatusBar, BorderLayout.SOUTH);
		StatusBar.setLayout(new GridLayout(0, 1, 0, 0));

		status = new JTextField();
		status.setText("LANステータス：オフライン");
		status.setEditable(false);
		StatusBar.add(status);
		status.setColumns(10);
	}

	public void setBoard(ReversiCell[][] c) {
		cell = c;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				mainBoard.add(cell[j][i]);
			}
		}
	}

	public void logOut(String s){
		logArea.setText(logArea.getText()+s+"\n");

	}

}
