package io.github.glaz_egy;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SendWait extends Thread {

	ReversiMain par;
	boolean end = false;
	public SendWait(ReversiMain m){
		par = m;
	}

	public void reset(){
		end = false;
	}

	@Override
	public void run() {
		while(!end){
			try {
				Reversi.datagramSend("wait:"+par.myAddr, InetAddress.getByName("255.255.255.255"), Reversi.CLIENT_PORT, true);
				Thread.sleep((long)(Math.random()*10));
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

}
