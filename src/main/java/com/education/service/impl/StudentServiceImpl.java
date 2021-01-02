package com.education.service.impl;

import java.util.List;

import com.education.bean.PageModel;
import com.education.bean.Student;
import com.education.dao.StudentDao;
import com.education.service.StudentService;

public class StudentServiceImpl implements StudentService{

	private static StudentDao dao = new StudentDao();
	@Override
	public List<Student> queryStudent() {
		return dao.queryStudent();
	}

	@Override
	public boolean addStudent(Student student) {
		return dao.addStudent(student);
	}

	@Override
	public boolean updateStudent(Student student) {
		return dao.updateStudent(student);
	}

	@Override
	public boolean deleteStudent(int id) {
		return dao.deleteStudent(id);
	}

	@Override
	public Student queryStudentById(int id) {
		return dao.queryStudentById(id);
	}

	@Override
	public List<Student> queryStudentByName(String name) {
		return dao.queryStudentByName(name);
	}

	@Override
	public List<Student> queryStudentByName(String name, PageModel page) {
		return null;
	}

	@Override
	public List<Student> queryStudentByPage(PageModel page) {
		return null;
	}
}
