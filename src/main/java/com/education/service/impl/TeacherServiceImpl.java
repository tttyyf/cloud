package com.education.service.impl;

import com.education.bean.PageModel;
import com.education.bean.Teacher;
import com.education.dao.TeacherDao;
import com.education.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    //创建dao层的对象调用数据库的操作方法
    TeacherDao dao = new TeacherDao();

    @Override
    public Teacher teacherLogin(Teacher teacher) {
        return dao.teacherLogin(teacher);
    }

    @Override
    public List<Teacher> queryTeacher() {
        return dao.queryTeacher();
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        return dao.insertTeacher(teacher);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return dao.updateTeacher(teacher);
    }

    @Override
    public boolean deleteTeacher(int id) {
        return dao.deleteTeacher(id);
    }

    @Override
    public Teacher queryTeacherById(int id) {
        return dao.queryTeacherById(id);
    }

    @Override
    public boolean queryTeacherByEmail(String email) {
        return dao.queryTeacherByEmail(email);
    }

    @Override
    public boolean queryTeacherByName(String name) {
        return dao.queryTeacherByName(name);
    }

    @Override
    public List<Teacher> queryTeacherByName(String name, PageModel page) {
        return dao.queryTeacherByName(name, page);
    }

    @Override
    public List<Teacher> queryTeacherByPage(PageModel page) {
        return dao.queryTeacherByPage(page);
    }
}
