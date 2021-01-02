package com.education.controller;

import com.education.bean.FileBean;
import com.education.bean.Manager;
import com.education.bean.PageModel;
import com.education.dao.FileDao;
import com.education.dao.ManagerDao;
import com.education.service.FileService;
import com.education.service.impl.FileServiceImpl;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/FileUp", "/FileDown", "/FileDelete", "/FileQuery", "/TeaFileQuery",
        "/TeaFileUp", "/FileQueryName", "/TeaFileQueryName"})
@MultipartConfig
public class FileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FileService fileService = new FileServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 获取uri来去判断具体访问的操作
        String uri = request.getRequestURI();
        System.out.println(uri);
        try {
            if (uri.equals("/TeachingManagement/FileUp")) {
                FileUp(request, response);
            } else if (uri.equals("/TeachingManagement/FileDown")) {
                FileDown(request, response);
            } else if (uri.equals("/TeachingManagement/FileDelete")) {
                FileDelete(request, response);
            } else if (uri.equals("/TeachingManagement/FileQuery")) {
                FileQuery(request, response);
            } else if (uri.equals("/TeachingManagement/TeaFileUp")) {
                TeaFileUp(request, response);
            } else if (uri.equals("/TeachingManagement/TeaFileQuery")) {
                TeaFileQuery(request, response);
            } else if (uri.equals("/TeachingManagement/FileQueryName")) {
                FileQueryName(request, response);
            } else if (uri.equals("/TeachingManagement/TeaFileQueryName")) {
                TeaFileQueryName(request, response);
            }
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
    }

    protected void FileQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, FileDao.queryFileCount());
        // 通过调用业务层的代码来操作数据库得到集合数据
        List<FileBean> list = fileService.queryFileByPage(page);
        // 1、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("FileList", list);
        request.getRequestDispatcher("fileManage/manager/fileTables.jsp").forward(request, response);
    }

    protected void TeaFileQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, FileDao.queryFileCount());
        // 通过调用业务层的代码来操作数据库得到集合数据
        List<FileBean> fileList = fileService.queryFileByPage(page);
        // 1、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("FileList", fileList);
        request.getRequestDispatcher("fileManage/teacher/fileTables.jsp").forward(request, response);
    }

    protected void FileQueryName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("f_name");
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, FileDao.queryFileByNameCount(name));
        // 2、通过调用业务层的代码来进行查询
        List<FileBean> list = fileService.queryFileByName(name, page);
        // 3、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        request.setAttribute("FileQueryName", name);
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("FileList", list);
        // 4、跳转页面
        request.getRequestDispatcher("fileManage/manager/fileTables.jsp").forward(request, response);
    }

    protected void TeaFileQueryName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("f_name");
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, FileDao.queryFileByNameCount(name));
        // 2、通过调用业务层的代码来进行查询
        List<FileBean> list = fileService.queryFileByName(name, page);
        // 3、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        request.setAttribute("FileQueryName", name);
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("FileList", list);
        // 4、跳转页面
        request.getRequestDispatcher("fileManage/teacher/fileTables.jsp").forward(request, response);
    }

    protected void FileUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SmartUploadException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = "E:/IDEA/JavaWebProject/TeachingManagement/web/UpLoad";
        SmartUpload smartUpload = new SmartUpload();//新建一个smartuplload对象
        smartUpload.initialize(this.getServletConfig(), request, response);//上传初始化
        smartUpload.upload(); //上传文件
        //将上传文件全部保存到指定的目录，必须保证upload目录在应用程序根文件夹中存在
        int count = smartUpload.save(path);
        File file = smartUpload.getFiles().getFile(0);
        if (count > 0) {
            String name = file.getFileName();
            int size = file.getSize();
            String filePath = path + "/" + name;

            FileBean f = new FileBean();
            f.setF_name(name);
            f.setF_size(size);
            f.setF_path(filePath);
            Boolean flag = fileService.addFile(f);

            if (flag) {
                List<FileBean> filelist = fileService.queryFile();
                JOptionPane.showMessageDialog(null, "文件上传成功！");
                request.getRequestDispatcher("/FileQuery").forward(request, response);
            } else {
                JOptionPane.showMessageDialog(null, "文件上传失败！");
                request.getRequestDispatcher("fileManage/manager/fileUp.jsp").forward(request, response);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择需上传的文件！");
            request.getRequestDispatcher("fileManage/manager/fileUp.jsp").forward(request, response);
        }
    }

    protected void TeaFileUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SmartUploadException {
        response.setContentType("text/html;charset=UTF-8");

        String path = "E:/IDEA/JavaWebProject/TeachingManagement/web/UpLoad";
        SmartUpload smartUpload = new SmartUpload();//新建一个smartuplload对象
        smartUpload.initialize(this.getServletConfig(), request, response);//上传初始化
        smartUpload.upload(); //上传文件
        //将上传文件全部保存到指定的目录，必须保证upload目录在应用程序根文件夹中存在
        int count = smartUpload.save(path);
        File file = smartUpload.getFiles().getFile(0);
        if (count > 0) {
            String name = file.getFileName();
            int size = file.getSize();
            String filePath = file.getFilePathName();

            FileBean f = new FileBean();
            f.setF_name(name);
            f.setF_size(size);
            f.setF_path(filePath);
            Boolean flag = fileService.addFile(f);
            if (flag) {
                List<FileBean> filelist = fileService.queryFile();
                JOptionPane.showMessageDialog(null, "文件上传成功！");
                request.getRequestDispatcher("/TeaFileQuery").forward(request, response);
            } else {
                JOptionPane.showMessageDialog(null, "文件上传失败！");
                request.getRequestDispatcher("fileManage/teacher/fileUp.jsp").forward(request, response);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择需上传的文件！");
            request.getRequestDispatcher("fileManage/teacher/fileUp.jsp").forward(request, response);
        }
    }

    protected void FileDown(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = "E:/IDEA/JavaWebProject/TeachingManagement/web/UpLoad";
        try {
            SmartUpload up = new SmartUpload();//新建一个smartuplload对象
            up.initialize(this.getServletConfig(), request, response);//初始化
            up.setContentDisposition(null);
            String filename = request.getParameter("f_name");
            up.downloadFile(path + "/" + filename);
            List<FileBean> filelist = fileService.queryFile();
        } catch (SmartUploadException e) {
            JOptionPane.showMessageDialog(null, "文件下载失败！");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "文件下载成功！");
        List<FileBean> filelist = fileService.queryFile();
        // 封装目的地
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:/IDEA/JavaWebProject/TeachingManagement/web/info/file", true));
        // 遍历集合
        for (FileBean ff : filelist) {
            // 写数据
            bw.write(ff.getF_name());
            bw.newLine();
            bw.flush();
        }
        request.getRequestDispatcher("fileManage/teacher/fileTables.jsp").forward(request, response);
    }

    protected void FileDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int confirm = JOptionPane.showConfirmDialog(null, "您确定删除该教学文件?", "提示", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String id = request.getParameter("f_id");
            if (id == "") {
                JOptionPane.showMessageDialog(null, "文件不能为空，删除失败！");
                request.getRequestDispatcher("fileManage/manager/fileDelete.jsp").forward(request, response);
            } else {
                int f_id = Integer.valueOf(id);
                Manager login = (Manager) request.getSession().getAttribute("login_manager");
                if (login == null) {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    boolean flag = fileService.deleteFile(f_id);
                    if (flag) {
                        List<FileBean> filelist = fileService.queryFile();
                        JOptionPane.showMessageDialog(null, "文件删除成功！");
                    } else {
                        JOptionPane.showMessageDialog(null, "文件删除失败！");
                    }
                }
            }
        }
        request.getRequestDispatcher("/FileQuery").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }
}
