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

    // 查找模糊查询专业的总数
    public static int queryFileByNameCount(String name) {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select count(*) as sum from file where f_name like ?";
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
    public List<FileBean> queryFileByName(String name, PageModel page) {
        List<FileBean> list = new ArrayList<FileBean>();
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from file where f_name like ? limit ?,?";
        try {
            // 2、通过链接对象来获取sql的语句对象
            PreparedStatement ps = connection.prepareStatement(sql);
            // 注入动态数据，名字进行注入
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, page.getStartIndex());
            ps.setLong(3, page.getPageSize());
            // 3、执行SQL 通过executeQuery方法执行查询语句,返回结果集对象
            ResultSet rs = ps.executeQuery();
            // 4、遍历结果集，从结果集中获取数据
            while (rs.next()) {// 判断是否有下一条数据，如果有则获取
                // 使用get方法来根据字段名来获取数据
                int f_id = rs.getInt("f_id");
                String f_name = rs.getString("f_name");
                long f_size = rs.getInt("f_size");
                String f_path = rs.getString("f_path");
                // 封装到对象中
                FileBean file = new FileBean(f_id, f_name, f_size, f_path);
                // 把对象添加到集合中
                list.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回集合对象
        return list;
    }

    // 根据page进行模糊查询的专业数据
    public List<FileBean> queryFileByPage(PageModel page) {
        List<FileBean> list = new ArrayList<FileBean>();
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select * from file limit ?,?";
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
                int f_id = rs.getInt("f_id");
                String f_name = rs.getString("f_name");
                long f_size = rs.getInt("f_size");
                String f_path = rs.getString("f_path");
                // 封装到对象中
                FileBean file = new FileBean(f_id, f_name, f_size, f_path);
                // 把对象添加到集合中
                list.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 返回集合对象
        return list;
    }

    // 专业的总记录数
    public static int queryFileCount() {
        // 1、定义sql语句 占位符?表示一个动态参数入参
        String sql = "select count(*) as sum from file";
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
