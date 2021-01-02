package com.education.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//������Ҫ�Ƕ���һ���������������ݿ�����Ӷ���
public class DBUtils {

	// 1���ṩ���ݿ�����Ӳ��� ip��ַ���˿ںš��û�������
	static String uri = "jdbc:mysql://localhost:3306/educationsystem?useUnicode=true&characterEncoding=utf8";
	static String username = "root";
	static String password = "123456";

	// ����һ�����ӵı���
	private static Connection connection;

	// ʹ��һ����̬��ʼ��������ʼ�����Ӷ���
	static {
		// 2������������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 3����ȡ���ݿ����� ͨ�����������ߵķ�������ȡ
		try {
			connection = DriverManager.getConnection(uri, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//����һ����̬�������������ݿ�����Ӷ���
	public static Connection getConnection() {
		return connection;
	}
}
