package httprequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HTTPRequest {

	public static String HostAdress = "128.30.52.100";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(HostAdress,80);			// 连接目的地址
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			
			pw.print(setRequest());
			pw.flush();				// 不flush可能不会发送出去
			
			
			//System.out.println(br.readLine());
//
//			Stream<String> response = br.lines();
//			System.out.println(response);
//			for (String string : response.collect(Collectors.toSet())) {
//				System.out.println(string);
//			}
			Thread.sleep(10);
			String response = new String();   // 对返回的信息进行处理
			String regex = " *<a.*href=\"(.*?)\".*>(.*)</a>";
			Pattern myPattern  = Pattern.compile(regex);
			
			PrintWriter fpw = new PrintWriter(new File("members.txt"));		// 将需要的信息写入members.txt中
			while(true) {								//读取返回的信息
				response = br.readLine();
				if(response == null) {
					break;
				}
				//System.out.println(response);" *<a.*>.*</a>"
				if(response.matches(regex)) {			// 是否以a标签开头 不要忘记了开头的空格
					System.out.println(response);
					Matcher myMatcher = myPattern.matcher(response);
//					while(myMatcher.find()) {				// 测试代码
//						System.out.println(myMatcher.group(1));
//						System.out.println(myMatcher.group(2));
//
//					}
					while(myMatcher.find()) {
						fpw.println(myMatcher.group(2)+" \nlink:"+myMatcher.group(1)+"\n");
					}
				}

			}
			
			socket.close();
			br.close();
			pw.close();
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,/*;q=0.8,application/signed-exchange;v=b3
	accept-encoding: gzip, deflate, br
	accept-language: zh-CN,zh;q=0.9
	cache-control: max-age=0
	cookie: _pk_ses.447.fbaa=1; _pk_id.447.fbaa=218a9a39ab72c916.1570358150.2.1570436857.1570434964.
	upgrade-insecure-requests: 1
	user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36
	 */
	public static String setRequest() {
		StringBuilder sb = new StringBuilder();
		sb.append("GET /Consortium/Member/List HTTP/1.0\r\n\r\n");
		sb.append("accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\r\n");
		sb.append("accept-encoding: gzip, deflate, br\r\n");
		sb.append("accept-language: zh-CN,zh;q=0.9");
		sb.append("cache-control: max-age=0\r\n");


		
		return sb.toString();
	}

}
