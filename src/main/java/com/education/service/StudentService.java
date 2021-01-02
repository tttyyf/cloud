package com.education.service;

import java.util.List;

import com.education.bean.PageModel;
import com.education.bean.Student;

public interface StudentService {
	
	public List<Student> queryStudent();
	
	public boolean addStudent(Student student);
	
	public boolean updateStudent(Student student);
	
	public boolean deleteStudent(int id);

	public List<Student> queryStudentByName(String name);

	public Student queryStudentById(int id);

	// ����name����ģ����ѯ��ѧ������
	public List<Student> queryStudentByName(String name, PageModel page);

	public List<Student> queryStudentByPage(PageModel page);
}
