package io.github.glaz_egy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class EULADialog extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EULADialog frame = new EULADialog();
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
	public EULADialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo5.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(3, 1, 0, 0));

		JRadioButton accept = new JRadioButton("上記利用規約に同意し、利用を開始する");


		JRadioButton refuse = new JRadioButton("上記利用規約に同意しない");

		ButtonGroup eula = new ButtonGroup();
		eula.add(accept);
		eula.add(refuse);
		panel.add(accept);
		panel.add(refuse);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_1);
		
		JButton OK = new JButton("OK");
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(OK);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		panel_1.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JTextArea txtrGlazegy = new JTextArea();
		txtrGlazegy.setEditable(false);
		txtrGlazegy.setLineWrap(true);
		txtrGlazegy.setText("プログラム利用規約\r\n\r\nGlaz-egy（以下、甲）が作成した本アプリケーション・プログラムを使用するもの（以下、乙）に対し、以下の利用規約が適用される。\r\n\r\n第一条（使用許諾）\r\n　本アプリケーション・プログラム（以下、本プログラム）はフリーソフトとし、利用用途を問わず、無料で使用できることを許諾する。\r\n\r\n第二条（著作権）\r\n　本プログラムの著作権は甲に帰属する。\r\n\r\n第三条（再配布・複製）\r\n　本プログラムの再配布・複製は常識的な範囲において許可する。\r\n\r\n第四条（改変）\r\n　本プログラムのソースコードの改変は可能である。\r\n　本プログラムはフリーソフトであり、GitHubにソースコードを挙げている。\r\n\r\n第五条（免責）\r\n　本プログラムの使用・改変によって何らかの障害を乙が被った場合であっても甲は一切の責任を負わない。");
		txtrGlazegy.setCaretPosition(0);
		scrollPane.setViewportView(txtrGlazegy);
	}

}
