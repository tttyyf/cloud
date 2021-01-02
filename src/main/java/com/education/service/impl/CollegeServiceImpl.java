package com.education.service.impl;

import java.util.List;

import com.education.bean.College;
import com.education.bean.PageModel;
import com.education.dao.CollegeDao;
import com.education.service.CollegeService;

public class CollegeServiceImpl implements CollegeService{
	private CollegeDao collegeDao = new CollegeDao();
	@Override
	public List<College> queryCollege() {
		return collegeDao.queryCollege();
	}

	@Override
	public boolean addCollege(College college) {
		return collegeDao.addCollege(college);
	}

	@Override
	public boolean updateCollege(College college) {
		return collegeDao.updateCollege(college);
	}

	@Override
	public boolean deleteCollege(int id) {
		return collegeDao.deleteCollege(id);
	}

	@Override
	public List<College> queryCollegeByName(String name) {
		return collegeDao.queryCollegeByName(name);
	}

	@Override
	public College queryCollegeById(int id) {
		return collegeDao.queryCollegeById(id);
	}

	@Override
	public List<College> queryCollegeByName(String name, PageModel page) {
		return null;
	}

	@Override
	public List<College> queryCollegeByPage(PageModel page) {
		return null;
	}
}
