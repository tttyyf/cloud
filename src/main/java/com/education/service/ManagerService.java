package com.education.service;

import java.util.List;

import com.education.bean.Manager;
import com.education.bean.PageModel;

public interface ManagerService {
    // ����һЩ�淶�Ľӿڷ���
    public Manager managerLogin(Manager manager);

    // ��ѯ���еĹ���Ա����
    public List<Manager> queryManager();

    // ������ݵķ���
    public boolean insertManager(Manager manager);

    // ɾ�����ݵķ���
    public boolean deleteManager(int id);

    // �޸����ݵķ���
    public boolean updateManager(Manager manager);

    // ����id��ѯ���ݵķ���
    public Manager queryManagerById(int id);

    //���ڵ�¼ʱ�ж��û��Ƿ����
    public boolean queryManagerByName(String name);

    //����ע��ʱ�ж������Ƿ��Ѵ���
    public boolean queryManagerByEmail(String email);

    // ����name����ģ����ѯ�Ĺ���Ա����
    public List<Manager> queryManagerByName(String name, PageModel page);

    public List<Manager> queryManagerByPage(PageModel page);
}
