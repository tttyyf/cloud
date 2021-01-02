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
import com.education.bean.Lesson;
import com.education.service.LessonService;
import com.education.service.impl.CollegeServiceImpl;
import com.education.service.impl.LessonServiceImpl;

@WebServlet(urlPatterns = {"/LessonQuery", "/TeaLessonQuery", "/LessonInsert", "/LessonUpdate",
        "/LessonToUpdate", "/LessonDelete", "/LessonQueryName", "/TeaLessonInsert", "/TeaLessonUpdate",
        "/TeaLessonQueryName", "/TeaLessonDelete", "/TeaLessonToUpdate"})
public class LessonController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LessonService lessonService = new LessonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/TeachingManagement/LessonQuery")) {
            LessonQuery(request, response);
        } else if (uri.equals("/TeachingManagement/TeaLessonQuery")) {
            TeaLessonQuery(request, response);
        } else if (uri.equals("/TeachingManagement/LessonInsert")) {
            LessonInsert(request, response);
        } else if (uri.equals("/TeachingManagement/TeaLessonInsert")) {
            TeaLessonInsert(request, response);
        } else if (uri.equals("/TeachingManagement/LessonUpdate")) {
            LessonUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeaLessonUpdate")) {
            TeaLessonUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/LessonToUpdate")) {
            LessonToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeaLessonToUpdate")) {
            TeaLessonToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/LessonDelete")) {
            LessonDelete(request, response);
        } else if (uri.equals("/TeachingManagement/TeaLessonDelete")) {
            TeaLessonDelete(request, response);
        } else if (uri.equals("/TeachingManagement/LessonQueryName")) {
            LessonQueryName(request, response);
        } else if (uri.equals("/TeachingManagement/TeaLessonQueryName")) {
            TeaLessonQueryName(request, response);
        }
    }

    private void LessonQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lesson> list = lessonService.queryLesson();
        request.getSession().setAttribute("LessonList", list);
        List<College> colList = new CollegeServiceImpl().queryCollege();
        request.getSession().setAttribute("CollegeList", colList);
        request.getRequestDispatcher("manager/LessonTables.jsp").forward(request, response);
    }

    private void TeaLessonQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lesson> list = lessonService.queryLesson();
        request.getSession().setAttribute("LessonList", list);
        List<College> colList = new CollegeServiceImpl().queryCollege();
        request.getSession().setAttribute("CollegeList", colList);
        request.getRequestDispatcher("teacher/LessonTables.jsp").forward(request, response);
    }

    private void LessonInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String l_name = request.getParameter("l_name");
        String score = request.getParameter("l_score");
        int l_score = Integer.parseInt(score);
        String time = request.getParameter("l_time");
        int l_time = Integer.parseInt(time);
        String l_describe = request.getParameter("l_describe");
        String require = request.getParameter("l_require");
        boolean l_require = false;
        if (require.equals("true")) {
            l_require = true;
        } else if (require.equals("false")) {
            l_require = false;
        }
        String id = request.getParameter("c_id");
        int c_id = Integer.parseInt(id);
        Lesson lesson = new Lesson();
        lesson.setL_name(l_name);
        lesson.setL_score(l_score);
        lesson.setL_time(l_time);
        lesson.setL_describe(l_describe);
        lesson.setL_require(l_require);
        lesson.setC_id(c_id);
        boolean isOk = lessonService.addLesson(lesson);
        if (isOk) {
            request.getRequestDispatcher("/LessonQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/addLesson.jsp").forward(request, response);
        }
    }

    private void TeaLessonInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String l_name = request.getParameter("l_name");
        String score = request.getParameter("l_score");
        int l_score = Integer.parseInt(score);
        String time = request.getParameter("l_time");
        int l_time = Integer.parseInt(time);
        String l_describe = request.getParameter("l_describe");
        String require = request.getParameter("l_require");
        boolean l_require = false;
        if (require.equals("true")) {
            l_require = true;
        } else if (require.equals("false")) {
            l_require = false;
        }
        String id = request.getParameter("c_id");
        int c_id = Integer.parseInt(id);
        Lesson lesson = new Lesson();
        lesson.setL_name(l_name);
        lesson.setL_score(l_score);
        lesson.setL_time(l_time);
        lesson.setL_describe(l_describe);
        lesson.setL_require(l_require);
        lesson.setC_id(c_id);
        boolean isOk = lessonService.addLesson(lesson);
        if (isOk) {
            request.getRequestDispatcher("/TeaLessonQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("teacher/addLesson.jsp").forward(request, response);
        }
    }

    private void LessonUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id1 = request.getParameter("l_id");
        int l_id = Integer.parseInt(id1);
        String l_name = request.getParameter("l_name");
        String score = request.getParameter("l_score");
        int l_score = Integer.parseInt(score);
        String time = request.getParameter("l_time");
        int l_time = Integer.parseInt(time);
        String l_describe = request.getParameter("l_describe");
        String require = request.getParameter("l_require");
        boolean l_require = false;
        if (require.equals("true")) {
            l_require = true;
        } else if (require.equals("false")) {
            l_require = false;
        }
        String id = request.getParameter("c_id");
        int c_id = Integer.parseInt(id);
        Lesson lesson = new Lesson(l_id, l_name, l_score, l_time, l_describe, l_require, c_id);
        boolean isOk = lessonService.updateLesson(lesson);
        if (isOk) {
            request.getRequestDispatcher("/LessonQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/LessonToUpdate?l_id = ?" + l_id).forward(request, response);
        }
    }

    private void TeaLessonUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id1 = request.getParameter("l_id");
        int l_id = Integer.parseInt(id1);
        String l_name = request.getParameter("l_name");
        String score = request.getParameter("l_score");
        int l_score = Integer.parseInt(score);
        String time = request.getParameter("l_time");
        int l_time = Integer.parseInt(time);
        String l_describe = request.getParameter("l_describe");
        String require = request.getParameter("l_require");
        boolean l_require = false;
        if (require.equals("true")) {
            l_require = true;
        } else if (require.equals("false")) {
            l_require = false;
        }
        String id = request.getParameter("c_id");
        int c_id = Integer.parseInt(id);
        Lesson lesson = new Lesson(l_id, l_name, l_score, l_time, l_describe, l_require, c_id);
        boolean isOk = lessonService.updateLesson(lesson);
        if (isOk) {
            request.getRequestDispatcher("/TeaLessonQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/TeaLessonToUpdate?l_id = ?" + l_id).forward(request, response);
        }
    }

    private void LessonToUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("l_id");
        int l_id = Integer.parseInt(id);
        Lesson lesson = lessonService.queryLessonById(l_id);
        request.setAttribute("UpdateLesson", lesson);
        request.getRequestDispatcher("manager/updateLesson.jsp").forward(request, response);
    }

    private void TeaLessonToUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("l_id");
        int l_id = Integer.parseInt(id);
        Lesson lesson = lessonService.queryLessonById(l_id);
        request.setAttribute("UpdateLesson", lesson);
        request.getRequestDispatcher("teacher/updateLesson.jsp").forward(request, response);
    }

    private void LessonDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flag = JOptionPane.showConfirmDialog(null, "您确定删除该课程?", "提示", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            String id = request.getParameter("l_id");
            int l_id = Integer.parseInt(id);
            boolean isok = lessonService.deleteLesson(l_id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('删除成功!！')</script>");
            } else {
                out.println("<script>alert('删除失败!！')</script>");
            }
        }
        request.getRequestDispatcher("LessonQuery").forward(request, response);
    }

    private void TeaLessonDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flag = JOptionPane.showConfirmDialog(null, "您确定删除该课程?", "提示", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            String id = request.getParameter("l_id");
            int l_id = Integer.parseInt(id);
            boolean isok = lessonService.deleteLesson(l_id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('删除成功!！')</script>");
            } else {
                out.println("<script>alert('删除失败!！')</script>");
            }
        }
        request.getRequestDispatcher("TeaLessonQuery").forward(request, response);
    }

    private void LessonQueryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String l_name = request.getParameter("l_name");
        request.setAttribute("LessonQueryName", l_name);
        List<Lesson> list = lessonService.LessonQueryByName(l_name);
        request.getSession().setAttribute("LessonList", list);
        List<College> colList = new CollegeServiceImpl().queryCollege();
        request.getSession().setAttribute("CollegeList", colList);

        request.getRequestDispatcher("manager/LessonTables.jsp").forward(request, response);
    }

    private void TeaLessonQueryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String l_name = request.getParameter("l_name");
        request.setAttribute("LessonQueryName", l_name);
        List<Lesson> list = lessonService.LessonQueryByName(l_name);
        request.getSession().setAttribute("LessonList", list);
        List<College> colList = new CollegeServiceImpl().queryCollege();
        request.getSession().setAttribute("CollegeList", colList);

        request.getRequestDispatcher("teacher/LessonTables.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
