package com.education.service;

import java.util.List;

import com.education.bean.College;
import com.education.bean.PageModel;

public interface CollegeService {
	public List<College> queryCollege();
	
	public boolean addCollege(College college);

	public boolean deleteCollege(int id);

	public boolean updateCollege(College college);
	
	public List<College> queryCollegeByName(String name);
	
	public College queryCollegeById(int id);

	// 根据name进行模糊查询的学院数据
	public List<College> queryCollegeByName(String name, PageModel page);

	public List<College> queryCollegeByPage(PageModel page);
}
