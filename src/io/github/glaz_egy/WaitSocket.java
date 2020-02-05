package io.github.glaz_egy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WaitSocket implements Runnable {

	private String addr;
	private String attackAddr;
	private boolean isAttack;

	public WaitSocket(String a){
		addr = a;
	}

	public boolean isAttack(){
		return isAttack;
	}

	public String attackAddr(){
		return attackAddr;
	}

	private void _wait(){
		try {
			ServerSocket myServerSocket = new ServerSocket(11453);
			Socket mySocket = myServerSocket.accept();
			System.out.println("Connect from"+mySocket.getInetAddress());
			BufferedReader myReader = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			String inputLine = myReader.readLine();
			String[] cmd = inputLine.split(",", 0);
			if(cmd[0] == "attack"){
				System.out.println(cmd[1]);
				attackAddr = mySocket.getInetAddress().getHostAddress();
				isAttack = true;
				myReader.close();
				mySocket.close();
				myServerSocket.close();
				return;
			}
			System.out.println(inputLine);
			System.out.println("Disconnect");
			myReader.close();
			mySocket.close();
			myServerSocket.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waiting(){
		isAttack = false;
		while(!isAttack){
			recieve();
			_wait();
		}
	}

	public void recieve(){
		try {
			Socket mySocket = new Socket("127.0.0.1", 11451);
			BufferedWriter myWriter = new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()));
			myWriter.write("wait:"+addr);
			myWriter.flush();
			myWriter.close();
			mySocket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		waiting();

	}

}
