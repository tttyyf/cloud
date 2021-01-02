package com.education.service;

import com.education.bean.PageModel;
import com.education.bean.Teacher;

import java.util.List;

public interface TeacherService {
    // 定义一些规范的接口方法
    public Teacher teacherLogin(Teacher teacher);

    // 查询所有的管理员数据
    public List<Teacher> queryTeacher();

    // 添加数据的方法
    public boolean insertTeacher(Teacher teacher);

    // 删除数据的方法
    public boolean deleteTeacher(int id);

    // 修改数据的方法
    public boolean updateTeacher(Teacher teacher);

    // 根据id查询数据的方法
    public Teacher queryTeacherById(int id);

    //用于登录时判断用户是否存在
    public boolean queryTeacherByName(String name);

    //用于注册时判断邮箱是否已存在
    public boolean queryTeacherByEmail(String email);

    // 根据name进行模糊查询的教师数据
    public List<Teacher> queryTeacherByName(String name, PageModel page);

    public List<Teacher> queryTeacherByPage(PageModel page);
}
