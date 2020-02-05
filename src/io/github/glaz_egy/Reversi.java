package io.github.glaz_egy;

import java.io.IOException;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Reversi {
	public static final int SERVER_PORT = 11453;
	public static final int CLIENT_PORT = 11452;
	public static final int GAME_PORT = 11451;
	public static void datagramSend(String msg, InetAddress addr, int port, boolean broadcast){
		try{
			DatagramSocket socket = new DatagramSocket();
	        socket.setBroadcast(broadcast);

	        byte[] buffer = msg.getBytes();

	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, addr, port);
	        socket.send(packet);
	        socket.close();
		}catch(ConnectException e){
			System.out.println("Port:"+port+" open terminal is not exist");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void datagramSend(String msg, String addr, int port, boolean broadcast){
		try {
			datagramSend(msg, InetAddress.getByName(addr), port, broadcast);
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static String[] datagramReceive(int port){
		byte[] buf = new byte[256];
		DatagramSocket socket;
		String[] re = new String[2];
		try {
			socket = new DatagramSocket(port);
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			InetAddress address = packet.getAddress();
			packet = new DatagramPacket(buf, buf.length, address, port);
			re[0] = new String(packet.getData(), 0, packet.getLength()).trim();
			re[1] = address.getHostAddress();
			socket.close();

		} catch (SocketException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return re;
	}
}
