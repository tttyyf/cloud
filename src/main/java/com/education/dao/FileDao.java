package com.education.dao;

import com.education.bean.FileBean;
import com.education.bean.PageModel;
import com.education.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDao {
    public static Connection connection;

    {
        try {
            connection = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<FileBean> queryFile() {
        String sql = "select * from file";
        List<FileBean> list = new ArrayList<FileBean>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int f_id = rs.getInt("f_id");
                String f_name = rs.getString("f_name");
                long f_size = rs.getInt("f_size");
                String f_path = rs.getString("f_path");
                FileBean fileBean = new FileBean(f_id, f_name, f_size, f_path);
                list.add(fileBean);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addFile(FileBean fileBean) {
        String sql = "insert into File (f_name,f_size,f_path) values (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, fileBean.getF_name());
            ps.setLong(2, fileBean.getF_size());
            ps.setString(3, fileBean.getF_path());
            int ret = ps.executeUpdate();
            if (ret > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFile(int id) {
        String sql = "delete from file where f_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int ret = ps.executeUpdate();
            if (ret > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateFile(FileBean fileBean) {
        String sql = "update file set f_name = ?,f_size = ?,f_path = ?  where f_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, fileBean.getF_name());
            ps.setLong(2, fileBean.getF_size());
            ps.setString(3, fileBean.getF_path());
            ps.setInt(4, fileBean.getF_id());

            int ret = ps.executeUpdate();
            if (ret > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<FileBean> queryFileByName(String name) {
        List<FileBean> list = new ArrayList<FileBean>();
        String sql = "select * from file where f_name = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                int f_id = rs.getInt("f_id");
                String f_name = rs.getString("f_name");
                long f_size = rs.getInt("f_size");
                String f_path = rs.getString("f_path");
                FileBean fileBean = new FileBean(f_id, f_name, f_size, f_path);
                list.add(fileBean);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FileBean queryFileById(int id) {
        String sql = "select * from file where f_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int f_id = rs.getInt("f_id");
                String f_name = rs.getString("f_name");
                long f_size = rs.getInt("f_size");
                String f_path = rs.getString("f_path");
                FileBean fileBean = new FileBean(f_id, f_name, f_size, f_path);
                return fileBean;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ����ģ����ѯרҵ������
    public static int queryFileByNameCount(String name) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select count(*) as sum from file where f_name like ?";
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
    public List<FileBean> queryFileByName(String name, PageModel page) {
        List<FileBean> list = new ArrayList<FileBean>();
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from file where f_name like ? limit ?,?";
        try {
            // 2��ͨ�����Ӷ�������ȡsql��������
            PreparedStatement ps = connection.prepareStatement(sql);
            // ע�붯̬���ݣ����ֽ���ע��
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, page.getStartIndex());
            ps.setLong(3, page.getPageSize());
            // 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
            ResultSet rs = ps.executeQuery();
            // 4��������������ӽ�����л�ȡ����
            while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
                // ʹ��get�����������ֶ�������ȡ����
                int f_id = rs.getInt("f_id");
                String f_name = rs.getString("f_name");
                long f_size = rs.getInt("f_size");
                String f_path = rs.getString("f_path");
                // ��װ��������
                FileBean file = new FileBean(f_id, f_name, f_size, f_path);
                // �Ѷ�����ӵ�������
                list.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ���ؼ��϶���
        return list;
    }

    // ����page����ģ����ѯ��רҵ����
    public List<FileBean> queryFileByPage(PageModel page) {
        List<FileBean> list = new ArrayList<FileBean>();
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from file limit ?,?";
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
                int f_id = rs.getInt("f_id");
                String f_name = rs.getString("f_name");
                long f_size = rs.getInt("f_size");
                String f_path = rs.getString("f_path");
                // ��װ��������
                FileBean file = new FileBean(f_id, f_name, f_size, f_path);
                // �Ѷ�����ӵ�������
                list.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ���ؼ��϶���
        return list;
    }

    // רҵ���ܼ�¼��
    public static int queryFileCount() {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select count(*) as sum from file";
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
