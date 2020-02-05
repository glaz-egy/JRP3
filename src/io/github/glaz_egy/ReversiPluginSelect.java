package io.github.glaz_egy;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ReversiPluginSelect extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ReversiMain mainData;
	private JTextArea Description;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ReversiPluginSelect(ReversiMain data) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				mainData.bot = null;
				mainData.botEnable(false);
				mainData.frame.botNothing.setSelected(true);
				setVisible(false);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		mainData = data;

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton fileOpen = new JButton("ファイルを開く");
		fileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadPlugin();
			}
		});
		panel.add(fileOpen);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		Description = new JTextArea();
		Description.setEditable(false);
		scrollPane.setViewportView(Description);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_1.add(ok);

		JButton cancel = new JButton("キャンセル");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainData.bot = null;
				mainData.botEnable(false);
				mainData.frame.botNothing.setSelected(true);
				setVisible(false);
			}
		});
		panel_1.add(cancel);
	}

	private void botPluginLoad(String filePath){
		File f = new File(filePath);
		try {
			JarFile jar = new JarFile(f);
			@SuppressWarnings("deprecation")
			URLClassLoader loader = new URLClassLoader(new URL[] { f.toURL() });
			for (Enumeration<JarEntry> e = jar.entries(); e.hasMoreElements();) {
				ZipEntry ze = (ZipEntry) e.nextElement();
				if (!ze.isDirectory() && ze.getName().endsWith(".class")) {
					Class<?> c = loader.loadClass(ze.getName().replace('/', '.').replace(".class", ""));
					if (BotTemplate.class.isAssignableFrom(c)) {
						System.out.println(c.getName() + " Is Plugin!");
						mainData.bot = (BotTemplate)c.newInstance();
						Description.setText(mainData.bot.description());
					}else{
						System.out.println(c.getName() + " Is Not Plugin!");
					}
				}
			}
			loader.close();
			jar.close();
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException error) {
			error.printStackTrace();
		}
	}

	public File loadPlugin() {
		File selectedFile=null;
		JFileChooser filechooser = new JFileChooser();
		int result = filechooser.showOpenDialog(this);
			switch(result){
			case (JFileChooser.APPROVE_OPTION):
				selectedFile = filechooser.getSelectedFile();
				String path = selectedFile.getPath();
				textField.setText(path);
				botPluginLoad(path);
				break;
			case (JFileChooser.CANCEL_OPTION):
				System.out.println("キャンセルされました");   break;
			case (JFileChooser.ERROR_OPTION):
			default:
				System.out.println("エラー又は取消しがありました");
			}
			return selectedFile;
	}

}
