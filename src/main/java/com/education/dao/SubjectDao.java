package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.education.bean.PageModel;
import com.education.bean.Subject;
import com.education.utils.DBUtils;

public class SubjectDao {
	public static Connection connection;

	static {
		try {
			connection = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Subject> querySubject(){
		List<Subject> list = new ArrayList<Subject>();
		String sql = "select * from subject";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int su_id = rs.getInt("su_id");
				String su_name = rs.getString("su_name");
				String su_describe = rs.getString("su_describe");
				int c_id = rs.getInt("c_id");
				Subject subject = new Subject(su_id , su_name , su_describe , c_id);
				list.add(subject);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addSubject(Subject subject) {
		String sql = "insert into subject (su_name , su_describe , c_id) values (? , ? , ?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1 ,  subject.getSu_name());
			ps.setString(2 ,  subject.getSu_describe());
			ps.setInt(3 ,  subject.getC_id());
			int isOk = ps.executeUpdate();
			if(isOk > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteSubject(int id) {
		String sql = "delete from subject where su_id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1 ,  id);
			int ret = ps.executeUpdate();
			if(ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateSubject(Subject subject) {
		String sql = "update subject set su_name = ? , su_describe = ? , c_id = ? where su_id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1 ,  subject.getSu_name());
			ps.setString(2 ,  subject.getSu_describe());
			ps.setInt(3 ,  subject.getC_id());
			ps.setInt(4 ,  subject.getSu_id());
			int ret = ps.executeUpdate();
			if(ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Subject> querySubjectByName(String name){
		List<Subject> list = new ArrayList<Subject>();
		String sql = "select * from subject where su_name = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1 ,  name);
			rs = ps.executeQuery();
			while(rs.next()) {
				int su_id = rs.getInt("su_id");
				String su_name = rs.getString("su_name");
				String su_describe = rs.getString("su_describe");
				int c_id = rs.getInt("c_id");
				Subject subject = new Subject(su_id , su_name , su_describe , c_id);
				list.add(subject);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Subject querySubjectById(int id) {
		String sql = "select * from subject where su_id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1 ,  id);
			rs = ps.executeQuery();
			while(rs.next()) {
				int su_id = rs.getInt("su_id");
				String su_name = rs.getString("su_name");
				String su_describe = rs.getString("su_describe");
				int c_id = rs.getInt("c_id");
				Subject subject = new Subject(su_id , su_name , su_describe , c_id);
				return subject;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查找模糊查询专业的总数
	public static int querySubjectByNameCount(String name) {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select count(*) as sum from subject where su_name like ?";
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

	// 根据name进行模糊查询的专业数据
	public List<Subject> querySubjectByName(String name, PageModel page) {
		List<Subject> list = new ArrayList<Subject>();
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from subject where su_name like ? limit ?,?";
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
				int su_id = rs.getInt("su_id");
				String su_name = rs.getString("su_name");
				String su_describe = rs.getString("su_describe");
				int c_id = rs.getInt("c_id");
				// 封装到对象中
				Subject subject = new Subject(su_id, su_name, su_describe, c_id);
				// 把对象添加到集合中
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回集合对象
		return list;
	}

	// 根据page进行模糊查询的专业数据
	public List<Subject> querySubjectByPage(PageModel page) {
		List<Subject> list = new ArrayList<Subject>();
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select * from subject limit ?,?";
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
				int su_id = rs.getInt("su_id");
				String su_name = rs.getString("su_name");
				String su_describe = rs.getString("su_describe");
				int c_id = rs.getInt("c_id");
				// 封装到对象中
				Subject subject = new Subject(su_id, su_name, su_describe, c_id);
				// 把对象添加到集合中
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回集合对象
		return list;
	}

	// 专业的总记录数
	public static int querySubjectCount() {
		// 1、定义sql语句 占位符?表示一个动态参数入参
		String sql = "select count(*) as sum from subject";
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
}
