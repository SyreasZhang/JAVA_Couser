package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("127.0.0.1",8040);			// 是连接服务器的IP和端口，不是本身的IP和端口
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while(true) {
				Menu baseMenu = new Menu();
				baseMenu.show();
				String request = baseMenu.makeChoice();
				if(!"".equals(request)){
					socket.getOutputStream().write(request.getBytes());
				}
				String response = br.readLine();
				printState(response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printState(String response) {
		System.out.print(getCommand(response));
		if("ok".equals(getState(response))) {
			System.out.println("成功");
		}else {
			System.out.println("失败 ");
			System.out.println("message:" +getMessge(response));
		}
	}
	
	public static String getCommand(String response) {
		int beginIndex = response.indexOf("command");
		return response.substring(response.indexOf('\"',beginIndex)+1,response.indexOf(' ',beginIndex)-1);
	}
	
	public static String getState(String response) {
		int beginIndex = response.indexOf("state");
		return response.substring(response.indexOf('\"',beginIndex)+1,response.indexOf(' ',beginIndex)-1);
	}
	
	public static String getMessge(String response) {
		int beginIndex = response.indexOf("message");
		return response.substring(response.indexOf('\"',beginIndex)+1,response.indexOf(' ',beginIndex)-1);
	}

}

class Menu{
	void show() {
		System.out.println("1、注册");
		System.out.println("2、登录");
		System.out.println("3、发送消息");
		System.out.println("4、接收消息");
		System.out.println("5、注销");
	}
	
	String makeChoice() {
		Scanner sc = new Scanner(System.in);
		String Choice = sc.nextLine();
		Integer choiceIndex;
		try{
			choiceIndex = new Integer(Choice);
		}catch (Exception e) {
			System.out.println("输入有误");
			return "";
		}
		String request;
		switch(choiceIndex) {
			case 1:
				request = "<register name=\"xu\"/>";
				break;
			case 2:
				request = "<login name=\"xu\" />";
				break;
			case 3:
				request = "<message from=\"xu\" to \"zhang\" message=\"this is a test\" />";
				break;
			case 4:
				request = "<logout name=\"xu\" />";
				break;
			default:
				System.out.println("输入有误");
				request = "";
		}
		
		return request;
	}
}
