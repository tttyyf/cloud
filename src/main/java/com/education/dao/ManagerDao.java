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
	// ��ȡ���ݿ����Ӷ���
	public static Connection connection;

	static {
		try {
			connection = DBUtils.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �����û��������ѯ����Ա����
	public Manager managerLogin(Manager manager) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from manager where e_name=? and e_password=?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
			ps.setString(1, manager.getE_name());
			ps.setString(2, manager.getE_password());
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ʹ��get�����������ֶ�������ȡ����
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// ��װ��������
				Manager managerLogin = new Manager(e_id, e_name, e_password,e_identity,e_email);
				// ����manager
				return managerLogin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؿ�
		return null;
	}

	// ��ѯ���еĹ���Ա����
	public List<Manager> queryManager() {
		List<Manager> list = new ArrayList<Manager>();
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from manager";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ʹ��get�����������ֶ�������ȡ����
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// ��װ��������
				Manager manager = new Manager(e_id, e_name, e_password,e_identity,e_email);
				// �Ѷ�����ӵ�������
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؼ��϶���
		return list;
	}

	// ������ݵķ���
	public boolean insertManager(Manager manager) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "insert into manager(e_name,e_password,e_identity,e_email) values(?,?,?,?)";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3��ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
			ps.setString(1, manager.getE_name());
			ps.setString(2, manager.getE_password());
			ps.setString(3, manager.getE_identity());
			ps.setString(4, manager.getE_email());
			// 4��ִ��SQL ��ӡ�ɾ�����޸Ķ�����ʹ��executeUpdateִ��
			int ret = ps.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// ɾ�����ݵķ���
	public boolean deleteManager(int id) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "delete from manager where e_id=?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3��ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
			ps.setInt(1, id);
			// 4��ִ��SQL ��ӡ�ɾ�����޸Ķ�����ʹ��executeUpdateִ��
			int ret = ps.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// �޸����ݵķ���
	public boolean updateManager(Manager manager) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "update manager set e_name=?,e_password=? where e_id=?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// 3��ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
			ps.setString(1, manager.getE_name());
			ps.setString(2, manager.getE_password());
			ps.setInt(3, manager.getE_id());
			// 4��ִ��SQL ��ӡ�ɾ�����޸Ķ�����ʹ��executeUpdateִ��
			int ret = ps.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// ����Ա���ݵ��ܼ�¼��
	public static int queryManagerCount() {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select count(*) as sum from manager";
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

	// ����id��ѯ����Ա����
	public Manager queryManagerById(int id) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from manager where e_id=?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
			ps.setInt(1, id);
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ʹ��get�����������ֶ�������ȡ����
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// ��װ��������
				Manager manager = new Manager(e_id, e_name, e_password,e_identity,e_email);
				// ����manager
				return manager;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؿ�
		return null;
	}

	// ����name��ѯ����Ա����
	public boolean queryManagerByName(String name) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from manager where e_name=?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
			ps.setString(1, name);
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ����true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����false
		return false;
	}

	// ����email��ѯ����Ա����
	public boolean queryManagerByEmail(String email) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from manager where e_email=?";
		try {
			// 2��ͨ�����Ӷ�������ȡsql��������
			PreparedStatement ps = connection.prepareStatement(sql);
			// ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
			ps.setString(1, email);
			// 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
			ResultSet rs = ps.executeQuery();
			// 4��������������ӽ�����л�ȡ����
			while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
				// ����true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����false
		return false;
	}

	// ����ģ����ѯ����Ա������
	public static int queryManagerByNameCount(String name) {
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select count(*) as sum from manager where e_name like ?";
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

	// ����name����ģ����ѯ�Ĺ���Ա����
	public List<Manager> queryManagerByName(String name, PageModel page) {
		List<Manager> list = new ArrayList<Manager>();
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from manager where e_name like ? limit ?,?";
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
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// ��װ��������
				Manager manager = new Manager(e_id, e_name, e_password,e_identity,e_email);
				System.out.println(manager);
				// �Ѷ�����ӵ�������
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؼ��϶���
		return list;
	}

	// ����page����ģ����ѯ�Ĺ���Ա����
	public List<Manager> queryManagerByPage(PageModel page) {
		List<Manager> list = new ArrayList<Manager>();
		// 1������sql��� ռλ��?��ʾһ����̬�������
		String sql = "select * from manager limit ?,?";
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
				int e_id = rs.getInt("e_id");
				String e_name = rs.getString("e_name");
				String e_password = rs.getString("e_password");
				String e_identity = rs.getString("e_identity");
				String e_email = rs.getString("e_email");
				// ��װ��������
				Manager manager = new Manager(e_id, e_name, e_password,e_identity,e_email);
				// �Ѷ�����ӵ�������
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؼ��϶���
		return list;
	}
}
