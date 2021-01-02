package com.education.dao;

import com.education.bean.PageModel;
import com.education.bean.Teacher;
import com.education.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {
    // ��ȡ���ݿ����Ӷ���
    public static Connection connection;

    static {
        try {
            connection = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // �����û��������ѯ��ʦ����
    public Teacher teacherLogin(Teacher teacher) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from teacher where t_name=? and t_password=?";
        try {
            // 2��ͨ�����Ӷ�������ȡsql��������
            PreparedStatement ps = connection.prepareStatement(sql);
            // ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
            ps.setString(1, teacher.getT_name());
            ps.setString(2, teacher.getT_password());
            // 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
            ResultSet rs = ps.executeQuery();
            // 4��������������ӽ�����л�ȡ����
            while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
                // ʹ��get�����������ֶ�������ȡ����
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // ��װ��������
                Teacher teacherLogin = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                // ����teacher
                return teacherLogin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ���ؿ�
        return null;
    }

    // ��ѯ���еĽ�ʦ����
    public List<Teacher> queryTeacher() {
        List<Teacher> list = new ArrayList<Teacher>();
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from teacher";
        try {
            // 2��ͨ�����Ӷ�������ȡsql��������
            PreparedStatement ps = connection.prepareStatement(sql);
            // 3��ִ��SQL ͨ��executeQuery����ִ�в�ѯ���,���ؽ��������
            ResultSet rs = ps.executeQuery();
            // 4��������������ӽ�����л�ȡ����
            while (rs.next()) {// �ж��Ƿ�����һ�����ݣ���������ȡ
                // ʹ��get�����������ֶ�������ȡ����
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // ��װ��������
                Teacher teacher = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                // �Ѷ�����ӵ�������
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ���ؼ��϶���
        return list;
    }

    // ��ӽ�ʦ���ݵķ���
    public boolean insertTeacher(Teacher teacher) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "insert into teacher(t_name,t_password,t_sex,t_identity,t_email,,l_id,t_phone) values(?,?,?,?,?,?,?)";
        try {
            // 2��ͨ�����Ӷ�������ȡsql��������
            PreparedStatement ps = connection.prepareStatement(sql);
            // 3��ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
            ps.setString(1, teacher.getT_name());
            ps.setString(2, teacher.getT_password());
            ps.setString(3, "Ů");
            ps.setString(4, "teacher");
            ps.setString(5, null);
            ps.setInt(6, 1);
            ps.setString(7, null);
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

    // ɾ����ʦ���ݵķ���
    public boolean deleteTeacher(int id) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "delete from teacher where t_id=?";
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

    // �޸Ľ�ʦ���ݵķ���
    public boolean updateTeacher(Teacher teacher) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "update teacher set t_name=?,t_password=?,t_sex=?,t_email=?,l_id=?,t_phone=? where t_id=?";
        try {
            // 2��ͨ�����Ӷ�������ȡsql��������
            PreparedStatement ps = connection.prepareStatement(sql);
            // 3��ע����� ʹ��set����ע�� ��һ��������ʾ���ǵڼ���ռλ��
            ps.setString(1, teacher.getT_name());
            ps.setString(2, teacher.getT_password());
            ps.setString(3, teacher.getT_sex());
            ps.setString(4, teacher.getT_email());
            ps.setInt(5, teacher.getL_id());
            ps.setString(6, teacher.getT_phone());
            ps.setInt(7, teacher.getT_id());
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

    // ��ʦ���ݵ��ܼ�¼��
    public static int queryTeacherCount() {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select count(*) as sum from teacher";
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

    // ����ģ����ѯ��ʦ������
    public static int queryTeacherByNameCount(String name) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select count(*) as sum from teacher where t_name like ?";
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

    // ����name����ģ����ѯ�Ľ�ʦ����
    public List<Teacher> queryTeacherByName(String name, PageModel page) {
        List<Teacher> list = new ArrayList<Teacher>();
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from teacher where t_name like ? limit ?,?";
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
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // ��װ��������
                Teacher teacher = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                System.out.println(teacher);
                // �Ѷ�����ӵ�������
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ���ؼ��϶���
        return list;
    }

    // ����page����ģ����ѯ�Ľ�ʦ����
    public List<Teacher> queryTeacherByPage(PageModel page) {
        List<Teacher> list = new ArrayList<Teacher>();
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from teacher limit ?,?";
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
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // ��װ��������
                Teacher teacher = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                // �Ѷ�����ӵ�������
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ���ؼ��϶���
        return list;
    }

    // ����id��ѯ��ʦ����
    public Teacher queryTeacherById(int id) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from teacher where t_id=?";
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
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // ��װ��������
                Teacher teacher = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                // ����teacher
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ���ؿ�
        return null;
    }

    // ����id��ѯ��ʦ����
    public boolean queryTeacherByName(String name) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from teacher where t_name=?";
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

    // ����email��ѯ��ʦ����
    public boolean queryTeacherByEmail(String email) {
        // 1������sql��� ռλ��?��ʾһ����̬�������
        String sql = "select * from teacher where t_email=?";
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
}
