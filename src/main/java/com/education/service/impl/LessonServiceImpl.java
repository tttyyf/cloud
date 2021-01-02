package com.education.service.impl;

import java.util.List;

import com.education.bean.Lesson;
import com.education.bean.PageModel;
import com.education.dao.LessonDao;
import com.education.service.LessonService;

public class LessonServiceImpl implements LessonService{
	private LessonDao lessonDao = new LessonDao();
	
	@Override
	public List<Lesson> queryLesson() {
		return lessonDao.queryLesson();
	}

	@Override
	public boolean addLesson(Lesson lesson) {
		return lessonDao.addLesson(lesson);
	}

	@Override
	public boolean updateLesson(Lesson lesson) {
		return lessonDao.updateLesson(lesson);
	}

	@Override
	public boolean deleteLesson(int id) {
		return lessonDao.deleteLesson(id);
	}

	@Override
	public List<Lesson> LessonQueryByName(String name) {
		return lessonDao.queryLessonByName(name);
	}

	@Override
	public Lesson queryLessonById(int id) {
		return lessonDao.queryLessonById(id);
	}

	@Override
	public List<Lesson> queryLessonByName(String name, PageModel page) {
		return null;
	}

	@Override
	public List<Lesson> queryLessonByPage(PageModel page) {
		return null;
	}
}
