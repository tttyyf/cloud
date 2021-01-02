package com.education.service.impl;

import java.util.List;

import com.education.bean.PageModel;
import com.education.bean.Subject;
import com.education.dao.SubjectDao;
import com.education.service.SubjectService;

public class SubjectServiceImpl implements SubjectService {
    SubjectDao dao = new SubjectDao();

    @Override
    public List<Subject> querySubject() {
        return dao.querySubject();
    }

    @Override
    public boolean addSubject(Subject subject) {
        return dao.addSubject(subject);
    }

    @Override
    public boolean updateSubject(Subject subject) {
        return dao.updateSubject(subject);
    }

    @Override
    public boolean deleteSubject(int id) {
        return dao.deleteSubject(id);
    }

    @Override
    public List<Subject> querySubjectByName(String name) {
        return dao.querySubjectByName(name);
    }

    @Override
    public Subject querySubjectById(int id) {
        return dao.querySubjectById(id);
    }

    @Override
    public List<Subject> querySubjectByName(String name, PageModel page) {
        return dao.querySubjectByName(name, page);
    }

    @Override
    public List<Subject> querySubjectByPage(PageModel page) {
        return dao.querySubjectByPage(page);
    }
}
