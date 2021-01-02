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

        // ��ȡuri��ȥ�жϾ�����ʵĲ���
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

        // ����һ��ҳ�����
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // ��������ת��
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, FileDao.queryFileCount());
        // ͨ������ҵ���Ĵ������������ݿ�õ���������
        List<FileBean> list = fileService.queryFileByPage(page);
        // 1�������ݱ�����ĳ�������������� ������session���Ự������
        // ����pagemodel�����ݵ���������
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("FileList", list);
        request.getRequestDispatcher("fileManage/manager/fileTables.jsp").forward(request, response);
    }

    protected void TeaFileQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // ����һ��ҳ�����
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // ��������ת��
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, FileDao.queryFileCount());
        // ͨ������ҵ���Ĵ������������ݿ�õ���������
        List<FileBean> fileList = fileService.queryFileByPage(page);
        // 1�������ݱ�����ĳ�������������� ������session���Ự������
        // ����pagemodel�����ݵ���������
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("FileList", fileList);
        request.getRequestDispatcher("fileManage/teacher/fileTables.jsp").forward(request, response);
    }

    protected void FileQueryName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // ����һ��ҳ�����
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // ��������ת��
            pageindex = Integer.parseInt(pageNo);
        }
        // 1������������û��������� ����key����ȡ
        String name = request.getParameter("f_name");
        // ����pageģ�͵Ķ���
        PageModel page = new PageModel(pageindex, FileDao.queryFileByNameCount(name));
        // 2��ͨ������ҵ���Ĵ��������в�ѯ
        List<FileBean> list = fileService.queryFileByName(name, page);
        // 3�������ݱ�����ĳ�������������� ������session���Ự������
        request.setAttribute("FileQueryName", name);
        // ����pagemodel�����ݵ���������
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("FileList", list);
        // 4����תҳ��
        request.getRequestDispatcher("fileManage/manager/fileTables.jsp").forward(request, response);
    }

    protected void TeaFileQueryName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        // ����һ��ҳ�����
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // ��������ת��
            pageindex = Integer.parseInt(pageNo);
        }
        // 1������������û��������� ����key����ȡ
        String name = request.getParameter("f_name");
        // ����pageģ�͵Ķ���
        PageModel page = new PageModel(pageindex, FileDao.queryFileByNameCount(name));
        // 2��ͨ������ҵ���Ĵ��������в�ѯ
        List<FileBean> list = fileService.queryFileByName(name, page);
        // 3�������ݱ�����ĳ�������������� ������session���Ự������
        request.setAttribute("FileQueryName", name);
        // ����pagemodel�����ݵ���������
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("FileList", list);
        // 4����תҳ��
        request.getRequestDispatcher("fileManage/teacher/fileTables.jsp").forward(request, response);
    }

    protected void FileUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SmartUploadException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = "E:/IDEA/JavaWebProject/TeachingManagement/web/UpLoad";
        SmartUpload smartUpload = new SmartUpload();//�½�һ��smartuplload����
        smartUpload.initialize(this.getServletConfig(), request, response);//�ϴ���ʼ��
        smartUpload.upload(); //�ϴ��ļ�
        //���ϴ��ļ�ȫ�����浽ָ����Ŀ¼�����뱣֤uploadĿ¼��Ӧ�ó�����ļ����д���
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
                JOptionPane.showMessageDialog(null, "�ļ��ϴ��ɹ���");
                request.getRequestDispatcher("/FileQuery").forward(request, response);
            } else {
                JOptionPane.showMessageDialog(null, "�ļ��ϴ�ʧ�ܣ�");
                request.getRequestDispatcher("fileManage/manager/fileUp.jsp").forward(request, response);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ�����ϴ����ļ���");
            request.getRequestDispatcher("fileManage/manager/fileUp.jsp").forward(request, response);
        }
    }

    protected void TeaFileUp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SmartUploadException {
        response.setContentType("text/html;charset=UTF-8");

        String path = "E:/IDEA/JavaWebProject/TeachingManagement/web/UpLoad";
        SmartUpload smartUpload = new SmartUpload();//�½�һ��smartuplload����
        smartUpload.initialize(this.getServletConfig(), request, response);//�ϴ���ʼ��
        smartUpload.upload(); //�ϴ��ļ�
        //���ϴ��ļ�ȫ�����浽ָ����Ŀ¼�����뱣֤uploadĿ¼��Ӧ�ó�����ļ����д���
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
                JOptionPane.showMessageDialog(null, "�ļ��ϴ��ɹ���");
                request.getRequestDispatcher("/TeaFileQuery").forward(request, response);
            } else {
                JOptionPane.showMessageDialog(null, "�ļ��ϴ�ʧ�ܣ�");
                request.getRequestDispatcher("fileManage/teacher/fileUp.jsp").forward(request, response);
            }
        } else {
            JOptionPane.showMessageDialog(null, "��ѡ�����ϴ����ļ���");
            request.getRequestDispatcher("fileManage/teacher/fileUp.jsp").forward(request, response);
        }
    }

    protected void FileDown(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = "E:/IDEA/JavaWebProject/TeachingManagement/web/UpLoad";
        try {
            SmartUpload up = new SmartUpload();//�½�һ��smartuplload����
            up.initialize(this.getServletConfig(), request, response);//��ʼ��
            up.setContentDisposition(null);
            String filename = request.getParameter("f_name");
            up.downloadFile(path + "/" + filename);
            List<FileBean> filelist = fileService.queryFile();
        } catch (SmartUploadException e) {
            JOptionPane.showMessageDialog(null, "�ļ�����ʧ�ܣ�");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "�ļ����سɹ���");
        List<FileBean> filelist = fileService.queryFile();
        // ��װĿ�ĵ�
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:/IDEA/JavaWebProject/TeachingManagement/web/info/file", true));
        // ��������
        for (FileBean ff : filelist) {
            // д����
            bw.write(ff.getF_name());
            bw.newLine();
            bw.flush();
        }
        request.getRequestDispatcher("fileManage/teacher/fileTables.jsp").forward(request, response);
    }

    protected void FileDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int confirm = JOptionPane.showConfirmDialog(null, "��ȷ��ɾ���ý�ѧ�ļ�?", "��ʾ", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String id = request.getParameter("f_id");
            if (id == "") {
                JOptionPane.showMessageDialog(null, "�ļ�����Ϊ�գ�ɾ��ʧ�ܣ�");
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
                        JOptionPane.showMessageDialog(null, "�ļ�ɾ���ɹ���");
                    } else {
                        JOptionPane.showMessageDialog(null, "�ļ�ɾ��ʧ�ܣ�");
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
