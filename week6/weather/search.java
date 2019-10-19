package weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;



@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cityName = new String(request.getParameter("city").getBytes("iso8859-1"),"UTF-8");
		System.out.println(cityName);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();

	
		try {
			GetWeather.connect(cityName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 发送页面
		File f = new File("weatherDisplay.xml");
		if(f.exists()) {
			BufferedReader br = new BufferedReader(
								new InputStreamReader(
								new FileInputStream(f),"utf-8"));

			String text = br.readLine();
			while(text!=null) {
				pw.println(text);
				text = br.readLine();
			}
			pw.flush();
			br.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
