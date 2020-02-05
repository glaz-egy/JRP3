package io.github.glaz_egy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ReversiSetting extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ReversiMain par;
	private HashMap<String, Font> fontList;
	private HashMap<String, String> fontFile;
	public JButton statusColorStringButton, statusColorBackButton, buttonColorStringButton
	, buttonColorBackButton, turnColorStringButton, turnColorBackButton, menuColorStringButton
	, menuColorBackButton, boardColorButton, stoneColorButton1, stoneColorButton2
	, backColorButton, turnLogColorStringButton, turnLogColorBackButton;
	public JComboBox<?> colorTheme;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReversiSetting frame = new ReversiSetting(new ReversiMain());
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ReversiSetting(ReversiMain mainData) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo5.png"));
		setTitle("JRP3 - 設定画面");
		UIManager.put("TabbedPane.font", new Font("メイリオ", Font.PLAIN, 12));
		UIManager.put("TitledBorder.font", new Font("メイリオ", Font.PLAIN, 12));
		par = mainData;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 560);
		fontList = new HashMap<String, Font>();
		fontFile = new HashMap<String, String>();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel gameSetting = new JPanel();
		tabbedPane.addTab("ゲーム設定", null, gameSetting, null);
		gameSetting.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		gameSetting.add(panel_2, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u30E6\u30FC\u30B6", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u8272", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u8868\u793A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 267, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
						.addComponent(panel_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
					.addContainerGap())
		);

		File fonts = new File("fonts");
		for(File item: fonts.listFiles()){
			try {
				Font f = Font.createFont(Font.TRUETYPE_FONT, item);
				fontList.put(f.getFontName(), f);
				fontFile.put(f.getFontName(), item.getPath().replace("\\", "\\\\"));
			} catch (FontFormatException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}

		JLabel lblFonnto = new JLabel("フォント※再起動が必要です");

		lblFonnto.setFont(new Font("メイリオ", Font.PLAIN, 14));

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(fontList.keySet().toArray(new String[fontList.size()])));
		JLabel fontTest = new JLabel("Aaあ");
		fontTest.setHorizontalAlignment(SwingConstants.CENTER);
		comboBox.setFont(new Font("メイリオ", Font.PLAIN, 14));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					Font f = fontList.get((String)e.getItem());
					par.setting.setFontFile(fontFile.get((String)e.getItem()));
					f = f.deriveFont(24.0f);
					fontTest.setFont(f);
				}
			}
		});



		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(comboBox, 0, 235, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(fontTest, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
							.addGap(11))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(lblFonnto)
							.addContainerGap(63, Short.MAX_VALUE))))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addComponent(lblFonnto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fontTest, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(180, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);

		JLabel theme = new JLabel("テーマ");
		theme.setFont(new Font("メイリオ", Font.PLAIN, 14));

		colorTheme = new JComboBox();
		colorTheme.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					changeTheme((String)e.getItem());
				}
			}
		});
		colorTheme.setModel(new DefaultComboBoxModel(new String[] {"通常", "ダーク", "ライト", "桜咲く", "Customize"}));
		colorTheme.setFont(new Font("メイリオ", Font.PLAIN, 14));

		JLabel boardColor = new JLabel("盤面");
		boardColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		boardColorButton = new ExtendedJButton("", 1);
		boardColorButton.addActionListener(new ColorSelectAction(this));

		JLabel stoneColor1 = new JLabel("石1(通常黒)");
		stoneColor1.setFont(new Font("メイリオ", Font.PLAIN, 14));

		stoneColorButton1 = new ExtendedJButton("", 2);
		stoneColorButton1.addActionListener(new ColorSelectAction(this));

		JLabel stoneColor2 = new JLabel("石1(通常白)");
		stoneColor2.setFont(new Font("メイリオ", Font.PLAIN, 14));

		stoneColorButton2 = new ExtendedJButton("", 3);
		stoneColorButton2.addActionListener(new ColorSelectAction(this));

		JLabel backColor = new JLabel("背景");
		backColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		backColorButton = new ExtendedJButton("", 4);
		backColorButton.addActionListener(new ColorSelectAction(this));

		JLabel turnLogStringColor = new JLabel("ターンログ(文字)");
		turnLogStringColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		turnLogColorStringButton = new ExtendedJButton("", 5);
		turnLogColorStringButton.addActionListener(new ColorSelectAction(this));

		JLabel turnLogBackColor = new JLabel("ターンログ(背景)");
		turnLogBackColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		turnLogColorBackButton = new ExtendedJButton("", 6);
		turnLogColorBackButton.addActionListener(new ColorSelectAction(this));

		JLabel statusStringColor = new JLabel("ステータス(文字)");
		statusStringColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		statusColorStringButton = new ExtendedJButton("", 7);
		statusColorStringButton.addActionListener(new ColorSelectAction(this));

		JLabel statusBackColor = new JLabel("ステータス(背景)");
		statusBackColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		statusColorBackButton = new ExtendedJButton("", 8);
		statusColorBackButton.addActionListener(new ColorSelectAction(this));

		JLabel buttonStringColor = new JLabel("ボタン(文字)");
		buttonStringColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		buttonColorStringButton = new ExtendedJButton("", 9);
		buttonColorStringButton.addActionListener(new ColorSelectAction(this));

		JLabel buttonBackColor = new JLabel("ボタン(背景)");
		buttonBackColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		buttonColorBackButton = new ExtendedJButton("", 10);
		buttonColorBackButton.addActionListener(new ColorSelectAction(this));

		JLabel turnStringColor = new JLabel("ターン表示(文字)");
		turnStringColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		turnColorStringButton = new ExtendedJButton("", 11);
		turnColorStringButton.addActionListener(new ColorSelectAction(this));

		JLabel turnBackColor = new JLabel("ターン表示(背景)");
		turnBackColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		turnColorBackButton = new ExtendedJButton("", 12);
		turnColorBackButton.addActionListener(new ColorSelectAction(this));

		JLabel menuStringColor = new JLabel("メニューバー(文字)");
		menuStringColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		menuColorStringButton = new ExtendedJButton("", 13);
		menuColorStringButton.addActionListener(new ColorSelectAction(this));

		JLabel menuBackColor = new JLabel("メニューバー(背景)");
		menuBackColor.setFont(new Font("メイリオ", Font.PLAIN, 14));

		menuColorBackButton = new ExtendedJButton("", 14);
		menuColorBackButton.addActionListener(new ColorSelectAction(this));


		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addComponent(colorTheme, 0, 176, Short.MAX_VALUE)
						.addComponent(theme, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(boardColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(stoneColor1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(stoneColor2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(backColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnLogStringColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnLogBackColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(statusStringColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(statusBackColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonStringColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonBackColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnStringColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnBackColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(menuStringColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(menuBackColor, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
								.addComponent(boardColorButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(stoneColorButton1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(stoneColorButton2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(backColorButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnLogColorStringButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnLogColorBackButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(statusColorStringButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(statusColorBackButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonColorStringButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonColorBackButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnColorStringButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(turnColorBackButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(menuColorStringButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(menuColorBackButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))))
					.addGap(19))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(theme)
					.addGap(4)
					.addComponent(colorTheme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addComponent(boardColor)
						.addComponent(boardColorButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
						.addComponent(stoneColor1)
						.addComponent(stoneColorButton1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
						.addComponent(stoneColor2)
						.addComponent(stoneColorButton2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(backColor)
							.addComponent(backColorButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(turnLogStringColor)
							.addComponent(turnLogColorStringButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(turnLogBackColor)
							.addComponent(turnLogColorBackButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(statusStringColor)
							.addComponent(statusColorStringButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(statusBackColor)
							.addComponent(statusColorBackButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(buttonStringColor)
							.addComponent(buttonColorStringButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(buttonBackColor)
							.addComponent(buttonColorBackButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(turnStringColor)
							.addComponent(turnColorStringButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(turnBackColor)
							.addComponent(turnColorBackButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(menuStringColor)
							.addComponent(menuColorStringButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
							.addComponent(menuBackColor)
							.addComponent(menuColorBackButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(129, Short.MAX_VALUE))
		);

		panel_5.setLayout(gl_panel_5);

		JLabel lblNewLabel = new JLabel("ユーザ名");
		lblNewLabel.setFont(new Font("メイリオ", Font.PLAIN, 14));

		textField = new JTextField();
		textField.setFont(new Font("メイリオ", Font.PLAIN, 14));
		textField.setColumns(10);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		panel_2.setLayout(gl_panel_2);
		tabbedPane.setEnabledAt(0, true);

		JPanel extendedSetting = new JPanel();
		tabbedPane.addTab("拡張設定", null, extendedSetting, null);
		extendedSetting.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		extendedSetting.add(panel_7, BorderLayout.CENTER);

				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u30ED\u30B0\u8A2D\u5B9A", TitledBorder.LEADING, TitledBorder.TOP, null, null));

						JLabel label = new JLabel("ログフォーマット");
						label.setFont(new Font("メイリオ", Font.PLAIN, 14));

								textField_1 = new JTextField();
								textField_1.setText("[YYYY-mm-dd][%logtype%] %logstring%");
								textField_1.setFont(new Font("メイリオ", Font.PLAIN, 14));
								textField_1.setColumns(10);

										JComboBox comboBox_1 = new JComboBox();
										comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"0 - Debag", "1 - Developer", "2 - User"}));
										comboBox_1.setFont(new Font("メイリオ", Font.PLAIN, 14));

												JLabel label_1 = new JLabel("ログレベル");
												label_1.setFont(new Font("メイリオ", Font.PLAIN, 14));

														textField_2 = new JTextField();
														textField_2.setFont(new Font("メイリオ", Font.PLAIN, 12));
														textField_2.setColumns(10);

																JButton button = new JButton("参照");
																button.setFont(new Font("メイリオ", Font.PLAIN, 12));

																		JLabel label_2 = new JLabel("ログファイル");
																		label_2.setFont(new Font("メイリオ", Font.PLAIN, 14));
																		GroupLayout gl_panel = new GroupLayout(panel);
																		gl_panel.setHorizontalGroup(
																			gl_panel.createParallelGroup(Alignment.LEADING)
																				.addGap(0, 545, Short.MAX_VALUE)
																				.addGroup(gl_panel.createSequentialGroup()
																					.addContainerGap()
																					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																						.addGroup(gl_panel.createSequentialGroup()
																							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
																							.addContainerGap())
																						.addGroup(gl_panel.createSequentialGroup()
																							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																							.addContainerGap())
																						.addGroup(gl_panel.createSequentialGroup()
																							.addComponent(label_1)
																							.addContainerGap())
																						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
																							.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
																							.addPreferredGap(ComponentPlacement.RELATED)
																							.addComponent(button)
																							.addGap(1))
																						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
																							.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
																							.addGap(425))
																						.addGroup(gl_panel.createSequentialGroup()
																							.addComponent(label)
																							.addContainerGap(473, Short.MAX_VALUE))))
																		);
																		gl_panel.setVerticalGroup(
																			gl_panel.createParallelGroup(Alignment.LEADING)
																				.addGap(0, 454, Short.MAX_VALUE)
																				.addGroup(gl_panel.createSequentialGroup()
																					.addComponent(label_2)
																					.addPreferredGap(ComponentPlacement.RELATED)
																					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addComponent(button))
																					.addPreferredGap(ComponentPlacement.RELATED)
																					.addComponent(label_1)
																					.addPreferredGap(ComponentPlacement.RELATED)
																					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																					.addPreferredGap(ComponentPlacement.RELATED)
																					.addComponent(label)
																					.addPreferredGap(ComponentPlacement.RELATED)
																					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																					.addContainerGap(255, Short.MAX_VALUE))
																		);
																		panel.setLayout(gl_panel);
																		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
																		gl_panel_7.setHorizontalGroup(
																			gl_panel_7.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_panel_7.createSequentialGroup()
																					.addContainerGap()
																					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
																					.addContainerGap())
																		);
																		gl_panel_7.setVerticalGroup(
																			gl_panel_7.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_panel_7.createSequentialGroup()
																					.addContainerGap()
																					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
																					.addContainerGap())
																		);
																		panel_7.setLayout(gl_panel_7);

		tabbedPane.setEnabledAt(1, true);

		switch(par.setting.getTheme()){
		case 0:
			(new NormalTheme()).setSettingColor(this);
			break;
		case 1:
			(new DarkTheme()).setSettingColor(this);
			break;
		case 2:
			(new LightTheme()).setSettingColor(this);
			break;
		case 3:
			(new SakuraTheme()).setSettingColor(this);
			break;
		case 4:
			par.setting.setSettingColor(this);
			break;
		default:
			break;
		}
		Font f;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File(par.setting.getFontFile()));
			f = f.deriveFont(24.0f);
			fontTest.setFont(f);
			comboBox.setSelectedItem(f.getFontName());
		} catch (FontFormatException | IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}



		colorTheme.setSelectedIndex(par.setting.getTheme());
		
				JPanel panel_3 = new JPanel();
				contentPane.add(panel_3, BorderLayout.SOUTH);
				FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				JButton saveButton = new JButton("保存");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						par.setting.setUserName(textField.getText());
						par.setting.saveSettings();
						setVisible(false);
						par.resetting();
					}
				});
				panel_3.add(saveButton);
				
						JButton cancelButton = new JButton("キャンセル");
						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								par.setting = par.setting.loadSettings(Setting4Reversi.SETTING_FILE);
								setVisible(false);
							}
						});
						panel_3.add(cancelButton);
		if(par.setting.getUserName() != null) textField.setText(par.setting.getUserName());
	}

	private void changeTheme(String theme){
		switch(theme){
		case "通常":
			(new NormalTheme()).setSettingColor(this);
			par.setting.setTheme(0);
			break;
		case "ダーク":
			(new DarkTheme()).setSettingColor(this);
			par.setting.setTheme(1);
			break;
		case "ライト":
			(new LightTheme()).setSettingColor(this);
			par.setting.setTheme(2);
			break;
		case "桜咲く":
			(new SakuraTheme()).setSettingColor(this);
			par.setting.setTheme(3);
			break;
		case "Customize":
			par.setting.setSettingColor(this);
			par.setting.setTheme(4);
			break;
		default:
			break;
		}
	}

	static class ColorSelectAction implements ActionListener{
		private ReversiSetting set;

		public ColorSelectAction(ReversiSetting s){
			set = s;
		}

		private void colorChange(ExtendedJButton source){
		    Color color = JColorChooser.showDialog(source, "色の選択", source.getForeground());
		    if(color != null){
		    	source.setBackground(color);
		    	System.out.println(source.ID);
		    	switch(source.ID){
		    	case 1:
		    		set.par.setting.setBoardColor(color);
		    		break;
		    	case 2:
		    		set.par.setting.setStone1Color(color);
		    		break;
		    	case 3:
		    		set.par.setting.setStone2Color(color);
		    		break;
		    	case 4:
		    		set.par.setting.setBackColor(color);
		    		break;
		    	case 5:
		    		set.par.setting.setTurnLogStringColor(color);
		    		break;
		    	case 6:
		    		set.par.setting.setTurnLogBackColor(color);
		    		break;
		    	case 7:
		    		set.par.setting.setStatusStringColor(color);
		    		break;
		    	case 8:
		    		set.par.setting.setStatusBackColor(color);
		    		break;
		    	case 9:
		    		set.par.setting.setButtonStringColor(color);
		    		break;
		    	case 10:
		    		set.par.setting.setButtonBackColor(color);
		    		break;
		    	case 11:
		    		set.par.setting.setTurnStringColor(color);
		    		break;
		    	case 12:
		    		set.par.setting.setTurnBackColor(color);
		    		break;
		    	case 13:
		    		set.par.setting.setMenuStringColor(color);
		    		break;
		    	case 14:
		    		set.par.setting.setMenuBackColor(color);
		    		break;
		    	default:
		    		break;
		    	}
		    }
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			set.colorTheme.setSelectedIndex(3);
			ExtendedJButton obj = (ExtendedJButton)e.getSource();
			colorChange(obj);
		}

	}

	static class ExtendedJButton extends JButton{
		public int ID;
		public ExtendedJButton(String string, int id) {
			super(string);
			ID = id;
		}

	}
}
