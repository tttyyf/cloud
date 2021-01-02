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

import com.education.bean.Student;
import com.education.bean.Subject;
import com.education.service.StudentService;
import com.education.service.impl.StudentServiceImpl;
import com.education.service.impl.SubjectServiceImpl;

@WebServlet(urlPatterns = {"/StudentQuery", "/TeaStudentQuery", "/StudentAdd", "/StudentUpdate",
        "/StudentToUpdate", "/StudentDelete", "/StudentQueryName", "/TeaStudentAdd", "/TeaStudentUpdate",
        "/TeaStudentToUpdate", "/TeaStudentDelete"})
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/TeachingManagement/StudentQuery")) {
            StudentQuery(request, response);
        } else if (uri.equals("/TeachingManagement/TeaStudentQuery")) {
            TeaStudentQuery(request, response);
        } else if (uri.equals("/TeachingManagement/StudentAdd")) {
            StudentAdd(request, response);
        } else if (uri.equals("/TeachingManagement/TeaStudentAdd")) {
            TeaStudentAdd(request, response);
        } else if (uri.equals("/TeachingManagement/StudentUpdate")) {
            StudentUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeaStudentUpdate")) {
            TeaStudentUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/StudentToUpdate")) {
            StudentToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeaStudentToUpdate")) {
            TeaStudentToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/StudentDelete")) {
            StudentDelete(request, response);
        } else if (uri.equals("/TeachingManagement/TeaStudentDelete")) {
            TeaStudentDelete(request, response);
        } else if (uri.equals("/TeachingManagement/StudentQueryName")) {
            StudentQueryName(request, response);
        } else if (uri.equals("/TeachingManagement/TeaStudentQueryName")) {
            TeaStudentQueryName(request, response);
        }
    }

    protected void StudentQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> list = studentService.queryStudent();
        request.getSession().setAttribute("StudentList", list);
        List<Subject> subjectList = new SubjectServiceImpl().querySubject();
        request.getSession().setAttribute("SubjectList", subjectList);

        request.getRequestDispatcher("manager/StudentTables.jsp").forward(request, response);
    }

    protected void TeaStudentQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> list = studentService.queryStudent();
        request.getSession().setAttribute("StudentList", list);
        List<Subject> subjectList = new SubjectServiceImpl().querySubject();
        request.getSession().setAttribute("SubjectList", subjectList);

        request.getRequestDispatcher("teacher/StudentTables.jsp").forward(request, response);
    }

    protected void StudentAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s_name = request.getParameter("s_name");
        String s_sex = request.getParameter("s_sex");
        String s_bright = request.getParameter("s_bright");
        String s_email = request.getParameter("s_email");
        String term = request.getParameter("s_term");
        int s_term = Integer.parseInt(term);
        String grate = request.getParameter("s_grate");
        int s_grate = Integer.parseInt(grate);
        String s_phone = request.getParameter("s_phone");
        String id = request.getParameter("su_id");
        int su_id = Integer.parseInt(id);
        String s_remark = request.getParameter("s_remark");

        Student student = new Student();
        student.setS_name(s_name);
        student.setS_sex(s_sex);
        student.setS_bright(s_bright);
        student.setS_email(s_email);
        student.setS_term(s_term);
        student.setS_grate(s_grate);
        student.setS_phone(s_phone);
        student.setSu_id(su_id);
        student.setS_remark(s_remark);
        boolean isOk = studentService.addStudent(student);
        System.out.println(isOk);
        if (isOk) {
            request.getRequestDispatcher("/StudentQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/addStudent.jsp").forward(request, response);
            ;
        }
    }

    protected void TeaStudentAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s_name = request.getParameter("s_name");
        String s_sex = request.getParameter("s_sex");
        String s_bright = request.getParameter("s_bright");
        String s_email = request.getParameter("s_email");
        String term = request.getParameter("s_term");
        int s_term = Integer.parseInt(term);
        String grate = request.getParameter("s_grate");
        int s_grate = Integer.parseInt(grate);
        String s_phone = request.getParameter("s_phone");
        String id = request.getParameter("su_id");
        int su_id = Integer.parseInt(id);
        String s_remark = request.getParameter("s_remark");

        Student student = new Student();
        student.setS_name(s_name);
        student.setS_sex(s_sex);
        student.setS_bright(s_bright);
        student.setS_email(s_email);
        student.setS_term(s_term);
        student.setS_grate(s_grate);
        student.setS_phone(s_phone);
        student.setSu_id(su_id);
        student.setS_remark(s_remark);
        boolean isOk = studentService.addStudent(student);
        System.out.println(isOk);
        if (isOk) {
            request.getRequestDispatcher("/TeaStudentQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("teacher/addStudent.jsp").forward(request, response);
        }
    }

    protected void StudentUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id1 = request.getParameter("s_id");
        int s_id = Integer.parseInt(id1);
        String s_name = request.getParameter("s_name");
        String s_sex = request.getParameter("s_sex");
        String s_bright = request.getParameter("s_bright");
        String s_email = request.getParameter("s_email");
        String term = request.getParameter("s_term");
        int s_term = Integer.parseInt(term);
        String grate = request.getParameter("s_grate");
        int s_grate = Integer.parseInt(grate);
        String s_phone = request.getParameter("s_phone");
        String id = request.getParameter("su_id");
        int su_id = Integer.parseInt(id);
        String s_remark = request.getParameter("s_remark");

        Student student = new Student(s_id, s_name, s_sex, s_bright, s_email, s_term, s_grate, s_phone, su_id, s_remark);
        boolean isOk = studentService.updateStudent(student);
        if (isOk) {
            request.getRequestDispatcher("/StudentQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/StudentToUpdate?e_id=" + s_id).forward(request, response);
        }
    }

    protected void TeaStudentUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id1 = request.getParameter("s_id");
        int s_id = Integer.parseInt(id1);
        String s_name = request.getParameter("s_name");
        String s_sex = request.getParameter("s_sex");
        String s_bright = request.getParameter("s_bright");
        String s_email = request.getParameter("s_email");
        String term = request.getParameter("s_term");
        int s_term = Integer.parseInt(term);
        String grate = request.getParameter("s_grate");
        int s_grate = Integer.parseInt(grate);
        String s_phone = request.getParameter("s_phone");
        String id = request.getParameter("su_id");
        int su_id = Integer.parseInt(id);
        String s_remark = request.getParameter("s_remark");

        Student student = new Student(s_id, s_name, s_sex, s_bright, s_email, s_term, s_grate, s_phone, su_id, s_remark);
        boolean isOk = studentService.updateStudent(student);
        if (isOk) {
            request.getRequestDispatcher("/TeaStudentQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/TeaStudentToUpdate?e_id=" + s_id).forward(request, response);
        }
    }

    protected void StudentToUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("s_id");
        int s_id = Integer.parseInt(id);
        Student student = studentService.queryStudentById(s_id);

        request.setAttribute("UpdateStudent", student);

        request.getRequestDispatcher("manager/updateStudent.jsp").forward(request, response);
    }

    protected void TeaStudentToUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("s_id");
        int s_id = Integer.parseInt(id);
        Student student = studentService.queryStudentById(s_id);

        request.setAttribute("UpdateStudent", student);

        request.getRequestDispatcher("teacher/updateStudent.jsp").forward(request, response);
    }


    protected void StudentDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flag = JOptionPane.showConfirmDialog(null, "您确定删除该学生信息?", "提示", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            String id = request.getParameter("s_id");
            int s_id = Integer.parseInt(id);
            boolean isok = studentService.deleteStudent(s_id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('删除成功!！')</script>");
            } else {
                out.println("<script>alert('删除失败!！')</script>");
            }
        }
        request.getRequestDispatcher("/StudentQuery").forward(request, response);
    }

    protected void TeaStudentDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flag = JOptionPane.showConfirmDialog(null, "您确定删除该学生信息?", "提示", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            String id = request.getParameter("s_id");
            int s_id = Integer.parseInt(id);
            boolean isok=studentService.deleteStudent(s_id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('删除成功!！')</script>");
            } else {
                out.println("<script>alert('删除失败!！')</script>");
            }
        }
        request.getRequestDispatcher("/TeaStudentQuery").forward(request, response);
    }

    protected void StudentQueryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s_name = request.getParameter("s_name");
        request.setAttribute("StudentQueryName", s_name);
        List<Student> list = studentService.queryStudentByName(s_name);
        request.getSession().setAttribute("StudentList", list);
        List<Subject> subjectList = new SubjectServiceImpl().querySubject();
        request.getSession().setAttribute("SubjectList", subjectList);

        request.getRequestDispatcher("manager/StudentTables.jsp").forward(request, response);
    }

    protected void TeaStudentQueryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s_name = request.getParameter("s_name");
        request.setAttribute("StudentQueryName", s_name);
        List<Student> list = studentService.queryStudentByName(s_name);
        request.getSession().setAttribute("StudentList", list);
        List<Subject> subjectList = new SubjectServiceImpl().querySubject();
        request.getSession().setAttribute("SubjectList", subjectList);

        request.getRequestDispatcher("teacher/StudentTables.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
