package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.education.bean.Student;
import com.education.utils.DBUtils;

public class StudentDao {
	public static Connection connection;

	static {
		try {
			connection = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> queryStudent(){
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int s_id = rs.getInt("s_id");
				String s_name = rs.getString("s_name");
				String s_sex = rs.getString("s_sex");
				String s_bright = rs.getString("s_bright");
				String s_email = rs.getString("s_email");
				int s_term = rs.getInt("s_term");
				int s_grate = rs.getInt("s_grate");
				String s_phone = rs.getString("s_phone");
				int su_id = rs.getInt("su_id");
				String s_remark  = rs.getString("s_remark");
				
				Student student = new Student(s_id , s_name , s_sex , s_bright 
						, s_email , s_term , s_grate , s_phone , su_id , s_remark);
				list.add(student);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addStudent(Student student) {
		String sql = "insert into student(s_name , s_sex , s_bright , s_email , s_term , s_grate , "
				+ "s_phone , su_id , s_remark) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1 , student.getS_name());
			ps.setString(2 , student.getS_sex());
			ps.setString(3 , student.getS_bright());
			ps.setString(4 , student.getS_email());
			ps.setInt(5 ,  student.getS_term());
			ps.setInt(6, student.getS_grate());
			ps.setString(7 , student.getS_phone());
			ps.setInt(8, student.getSu_id());
			ps.setString(9 , student.getS_remark());
			int num = ps.executeUpdate();
			if(num > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteStudent(int id) {
		String sql = "delete from student where s_id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1 , id);
			int num = ps.executeUpdate();
			if(num > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateStudent(Student student) {
		String sql = "update student set s_name = ? , s_sex = ? , s_bright = ? ,"
				+ " s_email = ? , s_term = ? , s_grate = ? , su_id = ? , s_remark = ? WHERE s_id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1 ,  student.getS_name());
			ps.setString(2 , student.getS_sex());
			ps.setString(3 , student.getS_bright());
			ps.setString( 4 , student.getS_email());
			ps.setInt(5 ,  student.getS_term());
			ps.setInt(6 ,  student.getS_grate());
			ps.setInt(7 ,  student.getSu_id());
			ps.setString(8 , student.getS_remark());
			ps.setInt( 9 , student.getS_id());
			int num = ps.executeUpdate();
			if(num > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Student queryStudentById(int id) {
		String sql = "select * from student where s_id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1 ,  id);
			rs = ps.executeQuery();
			while(rs.next()) {
				int s_id = rs.getInt("s_id");
				String s_name = rs.getString("s_name");
				String s_sex = rs.getString("s_sex");
				String s_bright = rs.getString("s_bright");
				String s_email = rs.getString("s_email");
				int s_term = rs.getInt("s_term");
				int s_grate = rs.getInt("s_grate");
				String s_phone = rs.getString("s_phone");
				int su_id = rs.getInt("su_id");
				String s_remark = rs.getString("s_remark");
				
				Student student = new Student(s_id , s_name , s_sex , s_bright 
						, s_email , s_term , s_grate , s_phone , su_id , s_remark);
				return student;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Student> queryStudentByName(String name){
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student where s_name like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1 ,  "%" + name + "%");
			rs = ps.executeQuery();
			while(rs.next()) {
				int s_id = rs.getInt("s_id");
				String s_name = rs.getString("s_name");
				String s_sex = rs.getString("s_sex");
				String s_bright = rs.getString("s_bright");
				String s_email = rs.getString("s_email");
				int s_term = rs.getInt("s_term");
				int s_grate = rs.getInt("s_grate");
				String s_phone = rs.getString("s_phone");
				int su_id = rs.getInt("su_id");
				String s_remark = rs.getString("s_remark");
				
				Student student = new Student(s_id , s_name , s_sex , s_bright 
						, s_email , s_term , s_grate , s_phone , su_id , s_remark);
				list.add(student);
				return list;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
