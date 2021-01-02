package com.education.service;

import java.util.List;

import com.education.bean.Lesson;
import com.education.bean.PageModel;

public interface LessonService {
	public List<Lesson> queryLesson();
	
	public boolean addLesson(Lesson lesson);

	public boolean deleteLesson(int id);

	public boolean updateLesson(Lesson lesson);
	
	public List<Lesson> LessonQueryByName(String name);
	
	public Lesson queryLessonById(int id);

	// ����name����ģ����ѯ�Ŀγ�����
	public List<Lesson> queryLessonByName(String name, PageModel page);

	public List<Lesson> queryLessonByPage(PageModel page);
}
