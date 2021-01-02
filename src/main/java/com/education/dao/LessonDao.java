package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.education.bean.Lesson;
import com.education.bean.Lesson;
import com.education.utils.DBUtils;

public class LessonDao {
    public static Connection connection;

    {
        try {
            connection = DBUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> queryLesson() {
        List<Lesson> list = new ArrayList<Lesson>();
        String sql = "select * from lesson";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int l_id = rs.getInt("l_id");
                String l_name = rs.getString("l_name");
                int l_score = rs.getInt("l_score");
                int l_time = rs.getInt("l_time");
                String l_describe = rs.getString("l_describe");
                boolean l_require = rs.getBoolean("l_require");
                int c_id = rs.getInt("c_id");
                Lesson lesson = new Lesson(l_id, l_name, l_score, l_time,
                        l_describe, l_require, c_id);
                list.add(lesson);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addLesson(Lesson lesson) {
        String sql = "insert into lesson (l_name , l_score , l_time , "
                + "l_describe , l_require , c_id) values (? , ? , ? , ? , ? , ?) ";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, lesson.getL_name());
            ps.setInt(2, lesson.getL_score());
            ps.setInt(3, lesson.getL_time());
            ps.setString(4, lesson.getL_describe());
            ps.setBoolean(5, lesson.getL_require());
            ps.setInt(6, lesson.getC_id());
            int ret = ps.executeUpdate();
            if (ret > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLesson(int id) {
        String sql = "delete from lesson where l_id = ?";
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

    public boolean updateLesson(Lesson lesson) {
        String sql = "update lesson set l_name = ? , l_score = ? , l_time = ? , l_describe = ? "
                + ", l_require = ? , c_id = ? where l_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, lesson.getL_name());
            ps.setInt(2, lesson.getL_score());
            ps.setInt(3, lesson.getL_time());
            ps.setString(4, lesson.getL_describe());
            ps.setBoolean(5, lesson.getL_require());
            ps.setInt(6, lesson.getC_id());
            ps.setInt(7, lesson.getL_id());
            int ret = ps.executeUpdate();
            if (ret > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Lesson> queryLessonByName(String name) {
        List<Lesson> list = new ArrayList<Lesson>();
        String sql = "select * from lesson where l_name = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                int l_id = rs.getInt("l_id");
                String l_name = rs.getString("l_score");
                int l_score = rs.getInt("l_score");
                int l_time = rs.getInt("l_time");
                String l_describe = rs.getString("l_describe");
                boolean l_require = rs.getBoolean("l_require");
                int c_id = rs.getInt("c_id");
                Lesson lesson = new Lesson(l_id, l_name, l_score, l_time, l_describe, l_require, c_id);
                list.add(lesson);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson queryLessonById(int id) {
        String sql = "select * from lesson where l_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int l_id = rs.getInt("l_id");
                String l_name = rs.getString("l_score");
                int l_score = rs.getInt("l_score");
                int l_time = rs.getInt("l_time");
                String l_describe = rs.getString("l_describe");
                boolean l_require = rs.getBoolean("l_require");
                int c_id = rs.getInt("c_id");
                Lesson lesson = new Lesson(l_id, l_name, l_score, l_time, l_describe, l_require, c_id);
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
