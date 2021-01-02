package com.education.service;

import java.util.List;

import com.education.bean.Manager;
import com.education.bean.PageModel;

public interface ManagerService {
    // 定义一些规范的接口方法
    public Manager managerLogin(Manager manager);

    // 查询所有的管理员数据
    public List<Manager> queryManager();

    // 添加数据的方法
    public boolean insertManager(Manager manager);

    // 删除数据的方法
    public boolean deleteManager(int id);

    // 修改数据的方法
    public boolean updateManager(Manager manager);

    // 根据id查询数据的方法
    public Manager queryManagerById(int id);

    //用于登录时判断用户是否存在
    public boolean queryManagerByName(String name);

    //用于注册时判断邮箱是否已存在
    public boolean queryManagerByEmail(String email);

    // 根据name进行模糊查询的管理员数据
    public List<Manager> queryManagerByName(String name, PageModel page);

    public List<Manager> queryManagerByPage(PageModel page);
}
