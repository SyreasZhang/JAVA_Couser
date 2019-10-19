package weather;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetWeather {
	
	 	static String city = "荆州";
	 	static String apiUrl1 = "https://www.tianqiapi.com/api/";
	 	static String appid = "25677911";
	 	static String appsecret = "U9HgBmbz";
	 	static String version = "v1";

	 	public static void main(String[] args) throws Exception {
	 		if(city==null) {
				city = "北京";
			}
			System.out.println(city+"in conect");
		    String apiUrl = String.format("%s?version=%s&city=%s&appid=%s&appsecret=%s",
		    						apiUrl1,version,city,appid,appsecret);
		    System.out.println(apiUrl);
		    URL url= new URL(apiUrl);
		    URLConnection open = url.openConnection();
		    InputStream input = open.getInputStream();
		    String result = IOUtils.toString(input,"utf-8");
		    
		    // 存json文件
		    PrintWriter pw = new PrintWriter(new File("test.json"));
		    pw.write(result);
		    pw.close();
		    InfoToXML(result);
		}
		public static void connect(String cityName) throws Exception {
			setCity(cityName);
			System.out.println(city+"in conect");
		    String apiUrl = String.format("%s?version=%s&city=%s&appid=%s&appsecret=%s",
		    						apiUrl1,version,city,appid,appsecret);
		    System.out.println(apiUrl);
		    URL url= new URL(apiUrl);
		    URLConnection open = url.openConnection();
		    InputStream input = open.getInputStream();
		    String result = IOUtils.toString(input,"utf-8");
		    
		    // 存json文件
		    PrintWriter pw = new PrintWriter(new File("test.json"));
		    pw.write(result);
		    pw.close();
		    InfoToXML(result);
			    	
		} // main
		  

		public static void InfoToXML(String result) {
			
			JSONObject dataJson = JSONObject.parseObject(result);	
			String cityid = dataJson.getString("cityid");			
			String cityName = dataJson.getString("city");	
			String update_time = dataJson.getString("update_time");
			if(!city.equals(cityName)) {								// ��������������ᵯ����������Ϣ,�����Ƿ���null
			    System.out.println("您输入的城市名有误");
			}	
		    JSONArray info = dataJson.getJSONArray("data");			// ȡ��һ������[]
		    System.out.println("cityInfo:"+cityName+cityid);
		   
			
		    Document document = DocumentHelper.createDocument();
		    Element root = document.addElement("weatherInfo");
		    Element city = root.addElement("cityName").addText(cityName);
		    Element cityId = root.addElement("cityid").addText(cityid);
		    Element updateTime = root.addElement("update_time").addText(update_time);
		    Element Date = root.addElement("date");
		    
		    for(int i = 0;i<info.size();i++){				// �ֱ��ȡÿ�����Ϣ
		    	Element daily = Date.addElement("daily_weather");
		    	JSONObject aDay = info.getJSONObject(i);	// {}
		    	// ����
		    	String day = aDay.getString("day");			// ����
		    	String date = aDay.getString("date");		// ��-��-��
		    	String week = aDay.getString("week");		// ����
		    	// ����
		    	String wea = aDay.getString("wea");			// ����״̬
		    	String wea_img = aDay.getString("wea_img");	
		    	// ��
		    	String win = aDay.getString("win");			// ����
		    	String win_speed = aDay.getString("win_speed");	// ����
		    	// �¶�
		    	String tem1 = aDay.getString("tem1");
		    	String tem2 = aDay.getString("tem2");
		    	String tem = aDay.getString("tem");
		    	daily.addElement("simpleDate").addAttribute("class", "element").addText(day);
		    	daily.addElement("date").addAttribute("class", "element").addText(date);
		    	daily.addElement("week").addAttribute("class", "element").addText(week);
		    	daily.addElement("wea").addAttribute("class", "element").addText(wea);
		    	daily.addElement("wea_img").addAttribute("class", "element").addText(wea_img);
		    	daily.addElement("win").addAttribute("class", "element").addText(win);
		    	daily.addElement("win_speed").addAttribute("class", "element").addText(win_speed);
		    	daily.addElement("tem1").addAttribute("class", "element").addText(tem1);
		    	daily.addElement("tem2").addAttribute("class", "element").addText(tem2);
		    	daily.addElement("tem").addAttribute("class", "element").addText(tem);
			    
			} //for
		    OutputFormat of = OutputFormat.createPrettyPrint();
		    try {
		    	XMLWriter xw = new XMLWriter(new FileOutputStream("weatherInfo.xml"),of);		// ���of��ʹxml��ʽ���ӱ�׼��
		    	xw.write(document);
		    	xw.close();
		    	System.out.println("创建成功");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	return;
		    }
		    try {
		    	File f = new File(".\\WebContent\\weatherDisplay.xml");
		    	if(f.exists()) {
		    		f.delete();
		    	}else {
		    	f.createNewFile();
		    	}
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), "utf-8"));
				BufferedReader br = new BufferedReader(
										new InputStreamReader(new FileInputStream("weatherInfo.xml"), "utf-8"));
				
				String temp = br.readLine();
				pw.println(temp);
				
				pw.println("<?xml-stylesheet type = \"text/css\" href = \"mycss.css\"?>");
				while((temp = br.readLine())!=null) {
					pw.println(temp);
				}
				pw.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // toXML


		public static String getCity() {
			return city;
		}


		public static void setCity(String city) {
			GetWeather.city = city;
		}
}
	 
