package com.education.controller;

import com.education.bean.Lesson;
import com.education.bean.Teacher;
import com.education.bean.PageModel;
import com.education.dao.TeacherDao;
import com.education.service.TeacherService;
import com.education.service.impl.LessonServiceImpl;
import com.education.service.impl.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/TeacherLogin", "/TeacherRegister", "/TeacherQueryName",
        "/TeacherQuery", "/TeaTeacherQuery", "/TeacherUpdate", "/TeacherInsert",
        "/TeacherToUpdate", "/TeacherDelete", "/TeaTeacherInsert", "/TeaTeacherDelete",
        "/TeaTeacherToUpdate", "/TeaTeacherUpdate", "/TeaTeacherQueryName", "/TeaPersonQuery",
        "/TeaPersonToUpdate", "/TeacherLoginServlet", "/TeacherRegisterServlet", "/TeacherLogOut"})
public class TeacherController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // 创建业务层的对象
    TeacherService teacherService = new TeacherServiceImpl();

    // 请求对象 响应对象
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取uri来去判断具体访问的操作
        String uri = request.getRequestURI();
        System.out.println(uri);

        if (uri.equals("/TeachingManagement/TeacherLogin")) {
            TeacherLogin(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherRegister")) {
            TeacherRegister(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherQuery")) {
            TeacherQuery(request, response);
        } else if (uri.equals("/TeachingManagement/TeaTeacherQuery")) {
            TeaTeacherQuery(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherInsert")) {
            TeacherInsert(request, response);
        } else if (uri.equals("/TeachingManagement/TeaTeacherInsert")) {
            TeaTeacherInsert(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherDelete")) {
            TeacherDelete(request, response);
        } else if (uri.equals("/TeachingManagement/TeaTeacherDelete")) {
            TeaTeacherDelete(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherToUpdate")) {
            TeacherToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeaTeacherToUpdate")) {
            TeaTeacherToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherUpdate")) {
            TeacherUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeaTeacherUpdate")) {
            TeaTeacherUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherQueryName")) {
            TeacherQueryName(request, response);
        } else if (uri.equals("/TeachingManagement/TeaTeacherQueryName")) {
            TeaTeacherQueryName(request, response);
        } else if (uri.equals("/TeachingManagement/TeaPersonQuery")) {
            TeaPersonQuery(request, response);
        } else if (uri.equals("/TeachingManagement/TeaPersonToUpdate")) {
            TeaPersonToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherLoginServlet")) {
            TeacherLoginServlet(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherRegisterServlet")) {
            TeacherRegisterServlet(request, response);
        } else if (uri.equals("/TeachingManagement/TeacherLogOut")) {
            TeacherLogOut(request, response);
        }
    }

    //根据name模糊查询教师数据
    protected void TeacherQueryName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("t_name");
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, TeacherDao.queryTeacherByNameCount(name));
        // 2、通过调用业务层的代码来进行查询
        List<Teacher> list = teacherService.queryTeacherByName(name, page);
        // 3、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        request.setAttribute("TeacherQueryName", name);
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("TeacherList", list);
        List<Lesson> lessonList = new LessonServiceImpl().queryLesson();
        request.getSession().setAttribute("LessonList", lessonList);
        // 4、跳转页面
        request.getRequestDispatcher("manager/TeacherTables.jsp").forward(request, response);
    }

    protected void TeaTeacherQueryName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("t_name");
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, TeacherDao.queryTeacherByNameCount(name));
        // 2、通过调用业务层的代码来进行查询
        List<Teacher> list = teacherService.queryTeacherByName(name, page);
        // 3、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        request.setAttribute("TeacherQueryName", name);
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("TeacherList", list);
        List<Lesson> lessonList = new LessonServiceImpl().queryLesson();
        request.getSession().setAttribute("LessonList", lessonList);
        // 4、跳转页面
        request.getRequestDispatcher("teacher/TeacherTables.jsp").forward(request, response);
    }

    protected void TeacherUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的用户名和密码 根据key来获取
        String t_id = request.getParameter("t_id");
        int id = Integer.parseInt(t_id);
        String name = request.getParameter("t_name");
        String password = request.getParameter("t_password");
        String sex = request.getParameter("t_sex");
        String identity = request.getParameter("t_identity");
        String email = request.getParameter("t_email");
        String lid = request.getParameter("l_id");
        int l_id = Integer.parseInt(lid);
        String phone = request.getParameter("t_phone");
        // 2、将用户名和密码封装到Teacher的对象中
        Teacher teacher = new Teacher(id, name, password, sex, identity, email, l_id, phone);
        // 3、通过调用业务层的代码来进行用户的修改
        boolean isok = teacherService.updateTeacher(teacher);
        // 4、根据结果进行页面跳转
        if (isok) {// 成功
            request.getRequestDispatcher("/TeacherQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/TeacherToUpdate?t_id=" + id).forward(request, response);
        }
    }

    protected void TeaTeacherUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的用户名和密码 根据key来获取
        String t_id = request.getParameter("t_id");
        int id = Integer.parseInt(t_id);
        String name = request.getParameter("t_name");
        String password = request.getParameter("t_password");
        String sex = request.getParameter("t_sex");
        String identity = request.getParameter("t_identity");
        String email = request.getParameter("t_email");
        String lid = request.getParameter("l_id");
        int l_id = Integer.parseInt(lid);
        String phone = request.getParameter("t_phone");
        // 2、将用户名和密码封装到Teacher的对象中
        Teacher teacher = new Teacher(id, name, password, sex, identity, email, l_id, phone);
        // 3、通过调用业务层的代码来进行用户的修改
        boolean isok = teacherService.updateTeacher(teacher);
        // 4、根据结果进行页面跳转
        if (isok) {// 成功
            request.getRequestDispatcher("/TeaTeacherQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/TeaTeacherToUpdate?t_id=" + id).forward(request, response);
        }
    }

    protected void TeacherToUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的id
        String e_id = request.getParameter("t_id");
        int id = Integer.parseInt(e_id);
        // 2、通过调用业务层的代码来进行用户的查询
        Teacher updateTeacher = teacherService.queryTeacherById(id);
        // 将数据保存在作用域中
        request.setAttribute("UpdateTeacher", updateTeacher);
        // 3、根据结果进行页面跳转
        request.getRequestDispatcher("manager/updateTeacher.jsp").forward(request, response);
    }

    protected void TeaTeacherToUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的id
        String t_id = request.getParameter("t_id");
        int id = Integer.parseInt(t_id);
        // 2、通过调用业务层的代码来进行用户的查询
        Teacher updateTeacher = teacherService.queryTeacherById(id);
        // 将数据保存在作用域中
        request.setAttribute("UpdateTeacher", updateTeacher);
        // 3、根据结果进行页面跳转
        request.getRequestDispatcher("teacher/updateTeacher.jsp").forward(request, response);
    }

    protected void TeaPersonToUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的id
        String t_id = request.getParameter("t_id");
        int id = Integer.parseInt(t_id);
        // 2、通过调用业务层的代码来进行用户的查询
        Teacher updateTeacher = teacherService.queryTeacherById(id);
        // 将数据保存在作用域中
        request.setAttribute("UpdateTeacher", updateTeacher);
        // 3、根据结果进行页面跳转
        request.getRequestDispatcher("teacher/updatePerson.jsp").forward(request, response);
    }

    protected void TeacherDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int flag = JOptionPane.showConfirmDialog(null, "您确定删除该学生信息?", "提示", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            // 1、接收请求的id
            String t_id = request.getParameter("t_id");
            int id = Integer.parseInt(t_id);
            // 2、通过调用业务层的代码来进行用户的删除
            boolean isok = teacherService.deleteTeacher(id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('删除成功!！')</script>");
            } else {
                out.println("<script>alert('删除失败!！')</script>");
            }
        }
        // 3、根据结果进行页面跳转
        request.getRequestDispatcher("/TeacherQuery").forward(request, response);
    }

    protected void TeaTeacherDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的id
        String t_id = request.getParameter("t_id");
        int id = Integer.parseInt(t_id);
        // 2、通过调用业务层的代码来进行用户的删除
        teacherService.deleteTeacher(id);
        // 3、根据结果进行页面跳转
        request.getRequestDispatcher("/TeaTeacherQuery").forward(request, response);
    }

    protected void TeacherInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("t_name");
        String password = request.getParameter("t_password");
        // 2、将用户名和密码封装到Teacher的对象中
        Teacher teacher = new Teacher();
        teacher.setT_name(name);
        teacher.setT_password(password);
        // 3、通过调用业务层的代码来进行用户的添加
        boolean isok = teacherService.insertTeacher(teacher);
        // 4、根据结果进行页面跳转
        if (isok) {// 成功
            request.getRequestDispatcher("/TeacherQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/addTeacher.jsp").forward(request, response);
        }
    }

    protected void TeaTeacherInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("t_name");
        String password = request.getParameter("t_password");
        // 2、将用户名和密码封装到Teacher的对象中
        Teacher teacher = new Teacher();
        teacher.setT_name(name);
        teacher.setT_password(password);
        // 3、通过调用业务层的代码来进行用户的添加
        boolean isok = teacherService.insertTeacher(teacher);
        // 4、根据结果进行页面跳转
        if (isok) {// 成功
            request.getRequestDispatcher("/TeaTeacherQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("teacher/addTeacher.jsp").forward(request, response);
        }
    }

    protected void TeacherLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 2、将用户名和密码封装到Teacher的对象中
        Teacher teacher = new Teacher();
        teacher.setT_name(name);
        teacher.setT_password(password);
        // 3、通过调用业务层的代码来进行用户的登录校验
        Teacher login_teacher = teacherService.teacherLogin(teacher);
        // 4、根据结果进行页面跳转
        if (login_teacher != null) {// 有查询到该用户，成功
            request.getSession().setAttribute("SESSION_username", name);
            request.getSession().setAttribute("login_teacher", login_teacher);
            request.getRequestDispatcher("/TeaTeacherQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void TeacherRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // 2、将用户名和密码封装到Teacher的对象中
        Teacher teacher = new Teacher();
        teacher.setT_name(name);
        teacher.setT_password(password);
        teacher.setT_identity("teacher");
        teacher.setT_email(email);

        // 3、通过调用业务层的代码来进行用户的注册
        boolean registerTeacher = new TeacherDao().insertTeacher(teacher);
        // 4、根据结果进行页面跳转
        if (registerTeacher) {//注册成功跳转到登录页面  重定向
            response.sendRedirect("/login.jsp");
        } else {//注册失败就返回注册页面  请求转发 /代表web
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    protected void TeacherLoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 2、将用户名和密码封装到teacher的对象中
        Teacher teacher = new Teacher();
        teacher.setT_name(name);
        teacher.setT_password(password);

        PrintWriter pw = response.getWriter();
        if (!teacherService.queryTeacherByName(name)) {
            pw.write("1"); //用户不存在
        } else if (teacherService.teacherLogin(teacher) == null) {
            pw.write("2"); //用户名或密码错误
        } else {
            pw.write("3"); //验证成功
        }
        pw.flush();
        pw.close();
    }

    protected void TeacherRegisterServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // 2、将用户名和密码封装到E_teacher的对象中
        Teacher teacher = new Teacher();
        teacher.setT_name(name);
        teacher.setT_password(password);
        teacher.setT_identity("teacher");
        teacher.setT_email(email);

        PrintWriter pw = response.getWriter();
        if (teacherService.queryTeacherByEmail(email)) {
            pw.write("1");
        } else if (email == null || !email.matches("([a-zA-Z0-9_-])(\\w|\\-)+@([a-zA-Z0-9])+\\.([a-zA-Z]{2,4})")) {
            pw.write("2");
        } else {
            pw.write("3");
        }
        pw.flush();
        pw.close();
    }

    protected void TeacherLogOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int flag = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit TeachingManagement?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            HttpSession session = request.getSession();
            session.removeAttribute("login_teacher");
            session.removeAttribute("SESSION_username");
            session.invalidate();
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/TeacherTables.jsp").forward(request, response);
        }
    }

    protected void TeacherQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, TeacherDao.queryTeacherCount());
        // 通过调用业务层的代码来操作数据库得到集合数据
        List<Teacher> list = teacherService.queryTeacherByPage(page);
        // 1、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("TeacherList", list);
        List<Lesson> lessonList = new LessonServiceImpl().queryLesson();
        request.getSession().setAttribute("LessonList", lessonList);
        // 2、跳转页面
        request.getRequestDispatcher("manager/TeacherTables.jsp").forward(request, response);
    }

    protected void TeaTeacherQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, TeacherDao.queryTeacherCount());
        // 通过调用业务层的代码来操作数据库得到集合数据
        List<Teacher> list = teacherService.queryTeacherByPage(page);
        // 1、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("TeacherList", list);
        List<Lesson> lessonList = new LessonServiceImpl().queryLesson();
        request.getSession().setAttribute("LessonList", lessonList);
        // 2、跳转页面
        request.getRequestDispatcher("teacher/TeacherTables.jsp").forward(request, response);
    }

    protected void TeaPersonQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, TeacherDao.queryTeacherCount());
        // 通过调用业务层的代码来操作数据库得到集合数据
        List<Teacher> list = teacherService.queryTeacherByPage(page);
        // 1、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("TeacherList", list);
        List<Lesson> lessonList = new LessonServiceImpl().queryLesson();
        request.getSession().setAttribute("LessonList", lessonList);
        // 2、跳转页面
        request.getRequestDispatcher("teacher/PersonTables.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }
}
