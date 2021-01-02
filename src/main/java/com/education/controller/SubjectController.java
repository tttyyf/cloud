package com.education.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import com.education.bean.College;
import com.education.bean.PageModel;
import com.education.bean.Subject;
import com.education.dao.SubjectDao;
import com.education.service.SubjectService;
import com.education.service.impl.CollegeServiceImpl;
import com.education.service.impl.SubjectServiceImpl;

@WebServlet(urlPatterns = {"/SubjectQuery", "/TeaSubjectQuery", "/SubjectInsert", "/SubjectUpdate",
        "/SubjectToUpdate", "/SubjectDelete", "/SubjectQueryName", "/TeaSubjectQueryName"})
public class SubjectController extends HttpServlet {
    SubjectService subjectService = new SubjectServiceImpl();

    private static final long serialVersionUID = 1L;

    public SubjectController() throws Exception {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/TeachingManagement/SubjectQuery")) {
            SubjectQuery(request, response);
        } else if (uri.equals("/TeachingManagement/TeaSubjectQuery")) {
            TeaSubjectQuery(request, response);
        } else if (uri.equals("/TeachingManagement/SubjectInsert")) {
            SubjectInsert(request, response);
        } else if (uri.equals("/TeachingManagement/SubjectUpdate")) {
            SubjectUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/SubjectToUpdate")) {
            SubjectToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/SubjectDelete")) {
            SubjectDelete(request, response);
        } else if (uri.equals("/TeachingManagement/SubjectQueryName")) {
            SubjectQueryName(request, response);
        } else if (uri.equals("/TeachingManagement/TeaSubjectQueryName")) {
            TeaSubjectQueryName(request, response);
        }
    }

    protected void SubjectQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, SubjectDao.querySubjectCount());
        // 通过调用业务层的代码来进行查询
        List<Subject> list = subjectService.querySubjectByPage(page);
        // 将数据保存在某个作用域（容器） 保存在session（会话作用域）
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("SubjectList", list);
        List<College> colList = new CollegeServiceImpl().queryCollege();
        request.getSession().setAttribute("CollegeList", colList);
        // 4、跳转页面
        request.getRequestDispatcher("manager/SubjectTables.jsp").forward(request, response);
    }

    protected void TeaSubjectQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, SubjectDao.querySubjectCount());
        // 通过调用业务层的代码来进行查询
        List<Subject> list = subjectService.querySubjectByPage(page);
        // 将数据保存在某个作用域（容器） 保存在session（会话作用域）
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("SubjectList", list);
        List<College> colList = new CollegeServiceImpl().queryCollege();
        request.getSession().setAttribute("CollegeList", colList);
        // 4、跳转页面
        request.getRequestDispatcher("teacher/SubjectTables.jsp").forward(request, response);
    }

    protected void SubjectQueryName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("su_name");
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, SubjectDao.querySubjectByNameCount(name));
        // 2、通过调用业务层的代码来进行查询
        List<Subject> list = subjectService.querySubjectByName(name, page);
        // 3、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        request.setAttribute("SubjectQueryName", name);
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("SubjectList", list);
        List<College> colList = new CollegeServiceImpl().queryCollege();
        request.getSession().setAttribute("CollegeList", colList);
        // 4、跳转页面
        request.getRequestDispatcher("manager/SubjectTables.jsp").forward(request, response);
    }

    protected void TeaSubjectQueryName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("su_name");
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, SubjectDao.querySubjectByNameCount(name));
        // 2、通过调用业务层的代码来进行查询
        List<Subject> list = subjectService.querySubjectByName(name, page);
        // 3、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        request.setAttribute("SubjectQueryName", name);
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("SubjectList", list);
        List<College> colList = new CollegeServiceImpl().queryCollege();
        request.getSession().setAttribute("CollegeList", colList);
        // 4、跳转页面
        request.getRequestDispatcher("teacher/SubjectTables.jsp").forward(request, response);
    }


    protected void SubjectInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String su_name = request.getParameter("su_name");
        String su_describe = request.getParameter("su_describe");
        String id = request.getParameter("c_id");
        int c_id = Integer.parseInt(id);

        Subject subject = new Subject();
        subject.setSu_name(su_name);
        subject.setSu_describe(su_describe);
        subject.setC_id(c_id);
        boolean isOk = subjectService.addSubject(subject);
        if (isOk) {
            request.getRequestDispatcher("/SubjectQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/addSubject.jsp").forward(request, response);
        }
    }

    protected void SubjectUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("su_id");
        int su_id = Integer.parseInt(id);
        String su_name = request.getParameter("su_name");
        String su_describe = request.getParameter("su_describe");
        String id1 = request.getParameter("c_id");
        int c_id = Integer.parseInt(id1);

        Subject subject = new Subject(su_id, su_name, su_describe, c_id);
        boolean isOk = subjectService.updateSubject(subject);
        if (isOk) {
            request.getRequestDispatcher("/SubjectQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/SubjectToUpdate?su_id =" + su_id).forward(request, response);
        }
    }

    protected void SubjectToUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("su_id");
        int su_id = Integer.parseInt(id);
        Subject subject = subjectService.querySubjectById(su_id);
        request.setAttribute("UpdateSubject", subject);
        request.getRequestDispatcher("manager/updateSubject.jsp").forward(request, response);
    }

    protected void SubjectDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flag = JOptionPane.showConfirmDialog(null, "您确定删除该学生信息?", "提示", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            String id = request.getParameter("su_id");
            int su_id = Integer.parseInt(id);
            boolean isok = subjectService.deleteSubject(su_id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('删除成功!！')</script>");
            } else {
                out.println("<script>alert('删除失败!！')</script>");
            }
        }
        request.getRequestDispatcher("/SubjectQuery").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
