package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServiceThread extends Thread{
	
	private Socket clientSocket;
	
	
	public ServiceThread(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}


	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			while(true) {
				String recive = br.readLine();
				System.out.println("接收到 "+clientSocket.getInetAddress().getHostAddress()+":"+recive);
				String response = DoEvent(recive);
				bw.write(response);
				bw.newLine();
				bw.flush();					// 将缓冲区的信息全部发送出去
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/*
	request = "<register name = \"xu\"/>";
				break;
			case 2:
				request = "<login name = \"xu\" />";
				break;
			case 3:
				request = "<message from = \"xu\" to \"zhang\" message = \"this is a test\" />";
				break;
			case 4:
				request = "<logout name = \"xu\" />";
	*
	*/
	public String DoEvent(String recive) {
		String result = "";
		if("<register name=\"xu\"/>".equals(recive)) {
			if(Math.random()*100>60) {
				result = "<result commnad=\"register\" state=\"error\" message=\"forbid\" />";
			}
			else {
				result = "<result command=\"register\" state = \"ok\" />";
			}
		} 
		if("<login name=\"xu\" />".equals(recive)) {
			if(Math.random()*100>60) {
				result = "<result command=\"login\" state=\"ok\" />";
			}
			else {
				result = "<result commnad=\"login\" state=\"error\" message=\"forbid\" />";
			}
		} 
		if("<message from=\"xu\" to \"zhang\" message=\"this is a test\" />".equals(recive)) {
			result = "<result command=\"message\" state=\"ok\" />";
		} 
		if( "<logout name = \"xu\" />".equals(recive)) {
			if(Math.random()*100>60) {
				result = "<result commnad=\"logout\" state=\"error\" message=\"forbid\" />";
			}
			else {
				result = "<result command=\"logout\" state=\"ok\" />";
			}
		} 
		return result;
	}
	
	
	public Socket getClientSocket() {
		return clientSocket;
	}


	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	

}
