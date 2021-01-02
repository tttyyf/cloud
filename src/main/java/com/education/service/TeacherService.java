package com.education.service;

import com.education.bean.PageModel;
import com.education.bean.Teacher;

import java.util.List;

public interface TeacherService {
    // ����һЩ�淶�Ľӿڷ���
    public Teacher teacherLogin(Teacher teacher);

    // ��ѯ���еĹ���Ա����
    public List<Teacher> queryTeacher();

    // ������ݵķ���
    public boolean insertTeacher(Teacher teacher);

    // ɾ�����ݵķ���
    public boolean deleteTeacher(int id);

    // �޸����ݵķ���
    public boolean updateTeacher(Teacher teacher);

    // ����id��ѯ���ݵķ���
    public Teacher queryTeacherById(int id);

    //���ڵ�¼ʱ�ж��û��Ƿ����
    public boolean queryTeacherByName(String name);

    //����ע��ʱ�ж������Ƿ��Ѵ���
    public boolean queryTeacherByEmail(String email);

    // ����name����ģ����ѯ�Ľ�ʦ����
    public List<Teacher> queryTeacherByName(String name, PageModel page);

    public List<Teacher> queryTeacherByPage(PageModel page);
}
