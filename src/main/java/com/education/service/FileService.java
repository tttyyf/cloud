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

    // 根据name进行模糊查询的教学文件数据
    public List<FileBean> queryFileByName(String name, PageModel page);

    public List<FileBean> queryFileByPage(PageModel page);
}
