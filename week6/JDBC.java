package demo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println("1°¢sql server\n2°¢mysql");
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		sc.close();
		String url = new String();
		String username = new String();
		String password = new String();
		if(choice.startsWith("1")) {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=mybase";
			username = "root";
			password = "123";
		}else if(choice.startsWith("2")) {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/mybase";
			username = "root";
			password = "123";
		}else {
			System.out.println(" ‰—°‘Ò”–ŒÛ");
		}
	
		Connection con = (Connection) DriverManager.getConnection(url, username, password);
		Statement stat = (Statement)con.createStatement();
		String sql_select = "SELECT * FROM sort";
		
		ResultSet rs = stat.executeQuery(sql_select);
		System.out.println(rs);
		
		while(rs.next()) {
//			int sid = rs.getInt("sid");
//			String sname = rs.getString("sname");
//			double sprice = rs.getDouble("sprice");
//			String sdesc = rs.getString("sdesc");
//			System.out.println(sid+" "+sname+" "+sprice+" "+sdesc);
			Object sid = rs.getObject("sid");
			Object sname = rs.getObject("sname");
			Object sprice = rs.getObject("sprice");
			Object sdesc = rs.getObject("sdesc");
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			System.out.println(sid+" "+sname+" "+sprice+" "+sdesc);
		}
		
		rs.close();
		stat.close();
		con.close();
	
	}

}
