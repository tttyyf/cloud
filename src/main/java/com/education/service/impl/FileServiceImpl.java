package com.education.service.impl;

import com.education.bean.FileBean;
import com.education.bean.PageModel;
import com.education.dao.FileDao;
import com.education.service.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {
    private FileDao fileDao = new FileDao();

    @Override
    public List<FileBean> queryFile() {
        return fileDao.queryFile();
    }

    @Override
    public boolean addFile(FileBean file) {
        return fileDao.addFile(file);
    }

    @Override
    public boolean deleteFile(int id) {
        return fileDao.deleteFile(id);
    }

    @Override
    public List<FileBean> queryFileByName(String name) {
        return fileDao.queryFileByName(name);
    }

    @Override
    public FileBean queryFileById(int id) {
        return fileDao.queryFileById(id);
    }

    @Override
    public List<FileBean> queryFileByName(String name, PageModel page) {
        return fileDao.queryFileByName(name, page);
    }

    @Override
    public List<FileBean> queryFileByPage(PageModel page) {
        return fileDao.queryFileByPage(page);
    }
}
