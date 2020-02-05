package io.github.glaz_egy;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class LANGameSocketControl implements Runnable{
	public InetAddress myHost;
	public ReversiMain main;
	public String myAddr;
	public WaitAttack wait;
	public SendWait send;
	public boolean isRunning;


	public LANGameSocketControl(ReversiMain mainData){
		main = mainData;
		try {
			myHost = InetAddress.getLocalHost();
			myAddr = myHost.getHostAddress();
			main.myAddr = myAddr;
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		wait = new WaitAttack(main);
		send = new SendWait(main);
	}

	public void endProcess(){
		main.sockEndCommand(Reversi.SERVER_PORT);
		send.end = true;
	}


	@Override
	public void run() {
		isRunning = true;
		(new Thread(wait)).start();
		(new Thread(send)).start();
		while(!wait.end && !wait.isAttack()){
			try {
				TimeUnit.MICROSECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		if(wait.isAttack()){
			System.out.println(wait.attackAddr());
			send.end = true;
			main.enemyAddr = wait.attackAddr();
			main.dialog.label.setText("<html>"+wait.attackAddr()+"から対戦を挑まれました<br>受けますか？<html>");
			main.dialog.setVisible(true);
		}
		wait.reset();
		send.reset();
	}

}
