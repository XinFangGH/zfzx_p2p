package com.hurong.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class JdbcConnection {

	public static Connection createConnn(){
		Connection conn = null;
		Properties p = new Properties();
		InputStream in = JdbcConnection.class.getResourceAsStream("/conf/jdbc.properties");
		try {
			p.load(in);
			String driver = p.getProperty("jdbc.driverClassName");
		    String url = p.getProperty("jdbc.url");//"jdbc:mysql://localhost:3306/qnjr";
		    String username =p.getProperty("jdbc.username");// "root";
		    String password =p.getProperty("jdbc.password"); //"root";
		    try {
		        Class.forName(driver); //classLoader,加载对应驱动
		        conn = (Connection) DriverManager.getConnection(url, username, password);
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    return conn;
	}
}
