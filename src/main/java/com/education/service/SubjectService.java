package com.education.service;

import java.util.List;

import com.education.bean.PageModel;
import com.education.bean.Subject;

public interface SubjectService {
	public List<Subject> querySubject();
	
	public boolean addSubject(Subject subject);

	public boolean deleteSubject(int id);

	public boolean updateSubject(Subject subject);
	
	public List<Subject> querySubjectByName(String name);
	
	public Subject querySubjectById(int id);

	// ����name����ģ����ѯ��רҵ����
	public List<Subject> querySubjectByName(String name, PageModel page);

	public List<Subject> querySubjectByPage(PageModel page);
}
