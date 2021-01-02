package com.education.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//该类主要是定义一个方法来返回数据库的链接对象
public class DBUtils {

	// 1、提供数据库的链接参数 ip地址、端口号、用户名密码
	static String uri = "jdbc:mysql://localhost:3306/educationsystem?useUnicode=true&characterEncoding=utf8";
	static String username = "root";
	static String password = "123456";

	// 定义一个链接的变量
	private static Connection connection;

	// 使用一个静态初始化块来初始化链接对象
	static {
		// 2、加载驱动类
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 3、获取数据库链接 通过驱动管理者的方法来获取
		try {
			connection = DriverManager.getConnection(uri, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//定义一个静态方法来返回数据库的链接对象
	public static Connection getConnection() {
		return connection;
	}
}
