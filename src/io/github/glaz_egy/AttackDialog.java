package io.github.glaz_egy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AttackDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AttackDialog dialog = new AttackDialog(new ReversiMain());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AttackDialog(ReversiMain main) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("..\\..\\..\\..\\logo5.png"));
		setTitle("JRP3 - アタックダイアログ");
		setBounds(100, 100, 300, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			label = new JLabel("");
			contentPanel.add(label, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("はい");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						yesAction(main);
						main.onlineBattle = true;
						main.onlineStart(true);
						setVisible(false);
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("いいえ");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						noAction(main);
						main.onlineBattle = false;
						(new Thread(main.sock)).start();
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	private void yesAction(ReversiMain main){
		Reversi.datagramSend("accept:"+main.sock.myAddr, main.sock.wait.attackAddr(), Reversi.GAME_PORT, false);
	}

	private void noAction(ReversiMain main){
		Reversi.datagramSend("refuse:"+main.sock.myAddr, main.sock.wait.attackAddr(), Reversi.CLIENT_PORT, false);
	}

}
