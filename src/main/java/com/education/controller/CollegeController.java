package com.education.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import com.education.bean.College;
import com.education.bean.Subject;
import com.education.service.CollegeService;
import com.education.service.impl.CollegeServiceImpl;
import com.education.service.impl.SubjectServiceImpl;

@WebServlet(urlPatterns = {"/CollegeQuery", "/TeaCollegeQuery", "/CollegeInsert", "/CollegeDelete", "/CollegeUpdate",
        "/CollegeToUpdate", "/CollegeQueryName", "/TeaCollegeQueryName"})
public class CollegeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CollegeService collegeService = new CollegeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/TeachingManagement/CollegeQuery")) {
            CollegeQuery(request, response);
        } else if (uri.equals("/TeachingManagement/TeaCollegeQuery")) {
            TeaCollegeQuery(request, response);
        } else if (uri.equals("/TeachingManagement/CollegeInsert")) {
            CollegeInsert(request, response);
        } else if (uri.equals("/TeachingManagement/CollegeDelete")) {
            CollegeDelete(request, response);
        } else if (uri.equals("/TeachingManagement/CollegeUpdate")) {
            CollegeUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/CollegeToUpdate")) {
            CollegeToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/CollegeQueryName")) {
            CollegeQueryName(request, response);
        } else if (uri.equals("/TeachingManagement/TeaCollegeQueryName")) {
            TeaCollegeQueryName(request, response);
        }
    }

    private void CollegeQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<College> list = collegeService.queryCollege();
        request.getSession().setAttribute("CollegeList", list);
        List<Subject> subList = new SubjectServiceImpl().querySubject();
        request.getSession().setAttribute("SubjectList", subList);
        request.getRequestDispatcher("manager/CollegeTables.jsp").forward(request, response);
    }

    private void TeaCollegeQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<College> list = collegeService.queryCollege();
        request.getSession().setAttribute("CollegeList", list);
        List<Subject> subList = new SubjectServiceImpl().querySubject();
        request.getSession().setAttribute("SubjectList", subList);
        request.getRequestDispatcher("teacher/CollegeTables.jsp").forward(request, response);
    }

    private void CollegeInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String c_name = request.getParameter("c_name");
        College college = new College();
        college.setC_name(c_name);
        boolean isOk = collegeService.addCollege(college);
        if (isOk) {
            request.getRequestDispatcher("/CollegeQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/addCollege.jsp").forward(request, response);
        }
    }

    private void CollegeDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flag = JOptionPane.showConfirmDialog(null, "您确定删除该学院?", "提示", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            String id = request.getParameter("c_id");
            int c_id = Integer.parseInt(id);
            boolean isok = collegeService.deleteCollege(c_id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('删除成功!！')</script>");
            } else {
                out.println("<script>alert('删除失败!！')</script>");
            }
        }
        request.getRequestDispatcher("/CollegeQuery").forward(request, response);
    }

    private void CollegeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("c_id");
        int c_id = Integer.parseInt(id);
        String c_name = request.getParameter("c_name");
        College college = new College(c_id, c_name);
        boolean isOk = collegeService.updateCollege(college);
        if (isOk) {
            request.getRequestDispatcher("/CollegeQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/CollegeToUpdate?c_id = " + c_id).forward(request, response);
        }
    }

    private void CollegeToUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("c_id");
        int c_id = Integer.parseInt(id);
        College college = collegeService.queryCollegeById(c_id);
        request.setAttribute("UpdateCollege", college);
        request.getRequestDispatcher("manager/updateCollege.jsp").forward(request, response);
    }

    private void CollegeQueryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<College> list = new ArrayList<College>();
        String c_name = request.getParameter("c_name");
        list = collegeService.queryCollegeByName(c_name);
        request.setAttribute("CollegeQueryName", c_name);
        request.getSession().setAttribute("CollegeList", list);
        request.getRequestDispatcher("manager/CollegeTables.jsp").forward(request, response);
    }

    private void TeaCollegeQueryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<College> list = new ArrayList<College>();
        String c_name = request.getParameter("c_name");
        list = collegeService.queryCollegeByName(c_name);
        request.setAttribute("CollegeQueryName", c_name);
        request.getSession().setAttribute("CollegeList", list);
        request.getRequestDispatcher("teacher/CollegeTables.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
