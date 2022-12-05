package com.zjl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
		String Driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/shop?useSSL=false&requireSSL=false&characterEncoding=UTF-8";
		String username="root";
		String pwd="123456";
		public Connection gConnection(){
			Connection connection=null;
			try {
				Class.forName(Driver);
				connection=DriverManager.getConnection(url, username, pwd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
			}
		public int zsg(String sql){
			int rows=0;
			try {
				Statement statement=gConnection().createStatement();
				rows=statement.executeUpdate(sql);
				gConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rows;
		}
		public ResultSet read(String sql){
			ResultSet rst=null;
			try {
				Statement statement=gConnection().createStatement();
				rst=statement.executeQuery(sql);
				gConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return rst;
		}

	public void close(ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
