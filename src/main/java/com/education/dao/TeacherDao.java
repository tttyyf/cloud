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
    // 获取数据库链接对象
    public static Connection connection;

    static {
        try {
            connection = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 根据用户名密码查询教师数据
    public Teacher teacherLogin(Teacher teacher) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from teacher where t_name=? and t_password=?";
        try {
            // 2、通过链接对象来获取sql的语句对象
            PreparedStatement ps = connection.prepareStatement(sql);
            // 注入参数 使用set方法注入 第一个参数表示的是第几个占位符
            ps.setString(1, teacher.getT_name());
            ps.setString(2, teacher.getT_password());
            // 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
            ResultSet rs = ps.executeQuery();
            // 4、遍历结果集，从结果集中获取数据
            while (rs.next()) {// 判断是否有下一条数据，如果有则获取
                // 使用get方法来根据字段名来获取数据
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // 封装到对象中
                Teacher teacherLogin = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                // 返回teacher
                return teacherLogin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回空
        return null;
    }

    // 查询所有的教师数据
    public List<Teacher> queryTeacher() {
        List<Teacher> list = new ArrayList<Teacher>();
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from teacher";
        try {
            // 2、通过链接对象来获取sql的语句对象
            PreparedStatement ps = connection.prepareStatement(sql);
            // 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
            ResultSet rs = ps.executeQuery();
            // 4、遍历结果集，从结果集中获取数据
            while (rs.next()) {// 判断是否有下一条数据，如果有则获取
                // 使用get方法来根据字段名来获取数据
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // 封装到对象中
                Teacher teacher = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                // 把对象添加到集合中
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回集合对象
        return list;
    }

    // 添加教师数据的方法
    public boolean insertTeacher(Teacher teacher) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "insert into teacher(t_name,t_password,t_sex,t_identity,t_email,,l_id,t_phone) values(?,?,?,?,?,?,?)";
        try {
            // 2、通过链接对象来获取sql的语句对象
            PreparedStatement ps = connection.prepareStatement(sql);
            // 3、注入参数 使用set方法注入 第一个参数表示的是第几个占位符
            ps.setString(1, teacher.getT_name());
            ps.setString(2, teacher.getT_password());
            ps.setString(3, "女");
            ps.setString(4, "teacher");
            ps.setString(5, null);
            ps.setInt(6, 1);
            ps.setString(7, null);
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

    // 删除教师数据的方法
    public boolean deleteTeacher(int id) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "delete from teacher where t_id=?";
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

    // 修改教师数据的方法
    public boolean updateTeacher(Teacher teacher) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "update teacher set t_name=?,t_password=?,t_sex=?,t_email=?,l_id=?,t_phone=? where t_id=?";
        try {
            // 2、通过链接对象来获取sql的语句对象
            PreparedStatement ps = connection.prepareStatement(sql);
            // 3、注入参数 使用set方法注入 第一个参数表示的是第几个占位符
            ps.setString(1, teacher.getT_name());
            ps.setString(2, teacher.getT_password());
            ps.setString(3, teacher.getT_sex());
            ps.setString(4, teacher.getT_email());
            ps.setInt(5, teacher.getL_id());
            ps.setString(6, teacher.getT_phone());
            ps.setInt(7, teacher.getT_id());
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

    // 教师数据的总记录数
    public static int queryTeacherCount() {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select count(*) as sum from teacher";
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

    // 查找模糊查询教师的总数
    public static int queryTeacherByNameCount(String name) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select count(*) as sum from teacher where t_name like ?";
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

    // 根据name进行模糊查询的教师数据
    public List<Teacher> queryTeacherByName(String name, PageModel page) {
        List<Teacher> list = new ArrayList<Teacher>();
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from teacher where t_name like ? limit ?,?";
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
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // 封装到对象中
                Teacher teacher = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                System.out.println(teacher);
                // 把对象添加到集合中
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回集合对象
        return list;
    }

    // 根据page进行模糊查询的教师数据
    public List<Teacher> queryTeacherByPage(PageModel page) {
        List<Teacher> list = new ArrayList<Teacher>();
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from teacher limit ?,?";
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
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // 封装到对象中
                Teacher teacher = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                // 把对象添加到集合中
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回集合对象
        return list;
    }

    // 根据id查询教师数据
    public Teacher queryTeacherById(int id) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from teacher where t_id=?";
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
                int t_id = rs.getInt("t_id");
                String t_name = rs.getString("t_name");
                String t_password = rs.getString("t_password");
                String t_identity = rs.getString("t_identity");
                String t_sex = rs.getString("t_sex");
                String t_email = rs.getString("t_email");
                int l_id = rs.getInt("l_id");
                String t_phone = rs.getString("t_phone");
                // 封装到对象中
                Teacher teacher = new Teacher(t_id, t_name, t_password, t_sex, t_identity, t_email, l_id, t_phone);
                // 返回teacher
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回空
        return null;
    }

    // 根据id查询教师数据
    public boolean queryTeacherByName(String name) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from teacher where t_name=?";
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

    // 根据email查询教师数据
    public boolean queryTeacherByEmail(String email) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from teacher where t_email=?";
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
}
