package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.education.bean.Manager;
import com.education.bean.PageModel;
import com.education.utils.DBUtils;

public class ManagerDao {
	// 获取数据库链接对象
	public static Connection connection;

	static {
		try {
			connection = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据用户名密码查询管理员数据
	public Manager managerLogin(Manager manager) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from manager where e_name=? and e_password=?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 注入参数 使用set方法注入 第一个参数表示的是第几个占位符
			ps.setString(1, manager.getE_name());
			ps.setString(2, manager.getE_password());
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			while (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 使用get方法来根据字段名来获取数据
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// 封装到对象中
				Manager managerLogin = new Manager(e_id, e_name, e_password,e_identity,e_email);
				// 返回manager
				return managerLogin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回空
		return null;
	}

	// 查询所有的管理员数据
	public List<Manager> queryManager() {
		List<Manager> list = new ArrayList<Manager>();
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from manager";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			while (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 使用get方法来根据字段名来获取数据
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// 封装到对象中
				Manager manager = new Manager(e_id, e_name, e_password,e_identity,e_email);
				// 把对象添加到集合中
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回集合对象
		return list;
	}

	// 添加数据的方法
	public boolean insertManager(Manager manager) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "insert into manager(e_name,e_password,e_identity,e_email) values(?,?,?,?)";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3、注入参数 使用set方法注入 第一个参数表示的是第几个占位符
			ps.setString(1, manager.getE_name());
			ps.setString(2, manager.getE_password());
			ps.setString(3, manager.getE_identity());
			ps.setString(4, manager.getE_email());
			// 4、执行SQL 添加、删除、修改都可以使用executeUpdate执行
			int ret = ps.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 删除数据的方法
	public boolean deleteManager(int id) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "delete from manager where e_id=?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3、注入参数 使用set方法注入 第一个参数表示的是第几个占位符
			ps.setInt(1, id);
			// 4、执行SQL 添加、删除、修改都可以使用executeUpdate执行
			int ret = ps.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 修改数据的方法
	public boolean updateManager(Manager manager) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "update manager set e_name=?,e_password=? where e_id=?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3、注入参数 使用set方法注入 第一个参数表示的是第几个占位符
			ps.setString(1, manager.getE_name());
			ps.setString(2, manager.getE_password());
			ps.setInt(3, manager.getE_id());
			// 4、执行SQL 添加、删除、修改都可以使用executeUpdate执行
			int ret = ps.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 管理员数据的总记录数
	public static int queryManagerCount() {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select count(*) as sum from manager";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			if (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 使用get方法来根据字段名来获取数据
				int sum = rs.getInt("sum");
				return sum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 没有则返回0
		return 0;
	}

	// 根据id查询管理员数据
	public Manager queryManagerById(int id) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from manager where e_id=?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 注入参数 使用set方法注入 第一个参数表示的是第几个占位符
			ps.setInt(1, id);
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			while (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 使用get方法来根据字段名来获取数据
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// 封装到对象中
				Manager manager = new Manager(e_id, e_name, e_password,e_identity,e_email);
				// 返回manager
				return manager;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回空
		return null;
	}

	// 根据name查询管理员数据
	public boolean queryManagerByName(String name) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from manager where e_name=?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 注入参数 使用set方法注入 第一个参数表示的是第几个占位符
			ps.setString(1, name);
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			while (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 返回true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回false
		return false;
	}

	// 根据email查询管理员数据
	public boolean queryManagerByEmail(String email) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from manager where e_email=?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 注入参数 使用set方法注入 第一个参数表示的是第几个占位符
			ps.setString(1, email);
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			while (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 返回true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回false
		return false;
	}

	// 查找模糊查询管理员的总数
	public static int queryManagerByNameCount(String name) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select count(*) as sum from manager where e_name like ?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 注入动态数据，名字进行注入
			ps.setString(1, "%" + name + "%");
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			if (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 使用get方法来根据字段名来获取数据
				int sum = rs.getInt("sum");
				return sum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 没有则返回0
		return 0;
	}

	// 根据name进行模糊查询的管理员数据
	public List<Manager> queryManagerByName(String name, PageModel page) {
		List<Manager> list = new ArrayList<Manager>();
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from manager where e_name like ? limit ?,?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 注入动态数据，名字进行注入
			ps.setString(1, "%" + name + "%");
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getPageSize());
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			while (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 使用get方法来根据字段名来获取数据
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// 封装到对象中
				Manager manager = new Manager(e_id, e_name, e_password,e_identity,e_email);
				System.out.println(manager);
				// 把对象添加到集合中
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回集合对象
		return list;
	}

	// 根据page进行模糊查询的管理员数据
	public List<Manager> queryManagerByPage(PageModel page) {
		List<Manager> list = new ArrayList<Manager>();
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from manager limit ?,?";
		try {
			// 2、通过链接对象来获取sql的语句对象
			PreparedStatement ps = connection.prepareStatement(sql);
			// 注入动态数据，名字进行注入
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getPageSize());
			// 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
			ResultSet rs = ps.executeQuery();
			// 4、遍历结果集，从结果集中获取数据
			while (rs.next()) {// 判断是否有下一条数据，如果有则获取
				// 使用get方法来根据字段名来获取数据
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// 封装到对象中
				Manager manager = new Manager(e_id, e_name, e_password,e_identity,e_email);
				// 把对象添加到集合中
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回集合对象
		return list;
	}
}
