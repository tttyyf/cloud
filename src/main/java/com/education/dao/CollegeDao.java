package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.education.bean.College;
import com.education.utils.DBUtils;


public class CollegeDao {
	private Connection connection;
	{
		try {
			connection = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<College> queryCollege() {
		String sql = "select * from college";
		List<College> list = new ArrayList<College>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_name = rs.getString("c_name");
				College college = new College(c_id , c_name);
				list.add(college);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addCollege(College college) {
		String sql = "insert into college (c_name) values (?)";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1 , college.getC_name());
			int ret = ps.executeUpdate();
			if(ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteCollege(int id) {
		String sql = "delete from college where c_id = ?";
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

	public boolean updateCollege(College college) {
		String sql = "update college set c_name = ? where c_id = ?";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1  , college.getC_name());
			ps.setInt(2 ,  college.getC_id());
			int ret = ps.executeUpdate();
			if(ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<College> queryCollegeByName(String name){
		List<College> list = new ArrayList<College>();
		String sql = "select * from college where c_name = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1 ,  name);
			rs = ps.executeQuery();
			while(rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_name = rs.getString("c_name");
				College college = new College(c_id , c_name);
				list.add(college);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public College queryCollegeById(int id) {
		String sql = "select * from college where c_id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1 ,  id);
			rs = ps.executeQuery();
			while(rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_name = rs.getString("c_name");
				College college = new College(c_id , c_name);
				return college;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
