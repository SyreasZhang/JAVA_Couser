package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceMain {

	public static void main(String[] args) throws IOException {
		ServerSocket lisentSocket = new ServerSocket(8040);					// 只用绑定端口就好了
		while(true) {
			Socket clientSocket = lisentSocket.accept();
			System.out.println(clientSocket.getInetAddress().getHostName()+"连接成功");
			new ServiceThread(clientSocket).start();
		}
	}
	
}
