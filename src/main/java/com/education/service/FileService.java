package com.education.service;

import com.education.bean.FileBean;
import com.education.bean.PageModel;

import java.util.List;

public interface FileService {
    public List<FileBean> queryFile();

    public boolean addFile(FileBean file);

    public boolean deleteFile(int id);

    public List<FileBean> queryFileByName(String name);

    public FileBean queryFileById(int id);

    // ����name����ģ����ѯ�Ľ�ѧ�ļ�����
    public List<FileBean> queryFileByName(String name, PageModel page);

    public List<FileBean> queryFileByPage(PageModel page);
}
