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

	// ����ģ����ѯרҵ������
	public static int querySubjectByNameCount(String name) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select count(*) as sum from subject where su_name like ?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// ע�붯̬���ݣ����ֽ���ע��
			ps.setString(1, "%" + name + "%");
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			if (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ʹ��get�����������ֶ�������ȡ����
				int sum = rs.getInt("sum");
				return sum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// û���򷵻�0
		return 0;
	}

	// ����name����ģ����ѯ��רҵ����
	public List<Subject> querySubjectByName(String name, PageModel page) {
		List<Subject> list = new ArrayList<Subject>();
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from subject where su_name like ? limit ?,?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// ע�붯̬���ݣ����ֽ���ע��
			ps.setString(1, "%" + name + "%");
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getPageSize());
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ʹ��get�����������ֶ�������ȡ����
				int su_id = rs.getInt("su_id");
				String su_name = rs.getString("su_name");
				String su_describe = rs.getString("su_describe");
				int c_id = rs.getInt("c_id");
				// ��װ��������
				Subject subject = new Subject(su_id, su_name, su_describe, c_id);
				// �Ѷ�����ӵ�������
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؼ��϶���
		return list;
	}

	// ����page����ģ����ѯ��רҵ����
	public List<Subject> querySubjectByPage(PageModel page) {
		List<Subject> list = new ArrayList<Subject>();
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from subject limit ?,?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// ע�붯̬���ݣ����ֽ���ע��
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getPageSize());
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ʹ��get�����������ֶ�������ȡ����
				int su_id = rs.getInt("su_id");
				String su_name = rs.getString("su_name");
				String su_describe = rs.getString("su_describe");
				int c_id = rs.getInt("c_id");
				// ��װ��������
				Subject subject = new Subject(su_id, su_name, su_describe, c_id);
				// �Ѷ�����ӵ�������
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؼ��϶���
		return list;
	}

	// רҵ���ܼ�¼��
	public static int querySubjectCount() {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select count(*) as sum from subject";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			if (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ʹ��get�����������ֶ�������ȡ����
				int sum = rs.getInt("sum");
				return sum;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// û���򷵻�0
		return 0;
	}
}
