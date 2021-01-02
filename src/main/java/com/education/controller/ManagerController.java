package com.education.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;

import com.education.bean.Manager;
import com.education.bean.PageModel;
import com.education.dao.ManagerDao;
import com.education.service.ManagerService;
import com.education.service.impl.ManagerServiceImpl;

@WebServlet(urlPatterns = {"/ManagerLogin", "/ManagerRegister", "/ManagerQueryName",
        "/ManagerQuery", "/ManagerUpdate", "/ManagerInsert", "/ManagerToUpdate", "/ManagerDelete",
        "/ManagerLogOut", "/ManagerLoginServlet", "/ManagerRegisterServlet"})
public class ManagerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // 创建业务层的对象
    ManagerService managerService = new ManagerServiceImpl();

    // 请求对象 响应对象
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取uri来去判断具体访问的操作
        String uri = request.getRequestURI();
        System.out.println(uri);

        if (uri.equals("/TeachingManagement/ManagerLogin")) {
            ManagerLogin(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerRegister")) {
            ManagerRegister(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerQuery")) {
            ManagerQuery(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerInsert")) {
            ManagerInsert(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerDelete")) {
            ManagerDelete(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerToUpdate")) {
            ManagerToUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerUpdate")) {
            ManagerUpdate(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerQueryName")) {
            ManagerQueryName(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerLogOut")) {
            ManagerLogOut(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerLoginServlet")) {
            ManagerLoginServlet(request, response);
        } else if (uri.equals("/TeachingManagement/ManagerRegisterServlet")) {
            ManagerRegisterServlet(request, response);
        }
    }

    protected void ManagerQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 定义一个页码变量
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // 进行数据转换
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, ManagerDao.queryManagerCount());
        // 通过调用业务层的代码来操作数据库得到集合数据
        List<Manager> list = managerService.queryManagerByPage(page);
        // 1、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("ManagerList", list);
        // 2、跳转页面
        request.getRequestDispatcher("manager/ManagerTables.jsp").forward(request, response);
    }

    protected void ManagerQueryName(HttpServletRequest request, HttpServletResponse response)
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
        String name = request.getParameter("e_name");
        // 创建page模型的对象
        PageModel page = new PageModel(pageindex, ManagerDao.queryManagerByNameCount(name));
        // 2、通过调用业务层的代码来进行查询
        List<Manager> list = managerService.queryManagerByName(name, page);
        // 3、将数据保存在某个作用域（容器） 保存在session（会话作用域）
        request.setAttribute("ManagerQueryName", name);
        // 保存pagemodel的数据到作用域中
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("ManagerList", list);
        // 4、跳转页面
        request.getRequestDispatcher("manager/ManagerTables.jsp").forward(request, response);
    }

    protected void ManagerUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1、接收请求的用户名和密码 根据key来获取
        String e_id = request.getParameter("e_id");
        int id = Integer.parseInt(e_id);
        String name = request.getParameter("e_name");
        String password = request.getParameter("e_password");
        String identity = request.getParameter("e_identity");
        String email = request.getParameter("e_email");
        // 2、将用户名和密码封装到Manager的对象中
        Manager manager = new Manager(id, name, password, identity, email);
        // 3、通过调用业务层的代码来进行用户的修改
        boolean isok = managerService.updateManager(manager);
        // 4、根据结果进行页面跳转
        if (isok) {// 成功
            request.getRequestDispatcher("/ManagerQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/ManagerToUpdate?e_id=" + id).forward(request, response);
        }
    }

    protected void ManagerToUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1、接收请求的id
        String e_id = request.getParameter("e_id");
        int id = Integer.parseInt(e_id);
        // 2、通过调用业务层的代码来进行用户的查询
        Manager updateManager = managerService.queryManagerById(id);
        // 将数据保存在作用域中
        request.setAttribute("UpdateManager", updateManager);
        // 3、根据结果进行页面跳转
        request.getRequestDispatcher("manager/updateManager.jsp").forward(request, response);
    }

    protected void ManagerDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int flag = JOptionPane.showConfirmDialog(null, "您确定删除该管理员信息?", "提示", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            // 1、接收请求的id
            String e_id = request.getParameter("e_id");
            int id = Integer.parseInt(e_id);
            // 2、通过调用业务层的代码来进行用户的删除
            boolean isok = managerService.deleteManager(id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('删除成功!！')</script>");
            } else {
                out.println("<script>alert('删除失败!！')</script>");
            }
        }
        // 3、根据结果进行页面跳转
        request.getRequestDispatcher("/ManagerQuery").forward(request, response);
    }

    protected void ManagerInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("e_name");
        String password = request.getParameter("e_password");
        // 2、将用户名和密码封装到Manager的对象中
        Manager manager = new Manager();
        manager.setE_name(name);
        manager.setE_password(password);
        // 3、通过调用业务层的代码来进行用户的添加
        boolean isok = managerService.insertManager(manager);
        // 4、根据结果进行页面跳转
        if (isok) {// 成功
            request.getRequestDispatcher("/ManagerQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/addManager.jsp").forward(request, response);
        }
    }

    protected void ManagerLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 2、将用户名和密码封装到Manager的对象中
        Manager manager = new Manager();
        manager.setE_name(name);
        manager.setE_password(password);
        // 3、通过调用业务层的代码来进行用户的登录校验
        Manager login_manager = managerService.managerLogin(manager);
        // 4、根据结果进行页面跳转
        if (login_manager != null) {// 有查询到该用户，成功
            HttpSession session = request.getSession();
            session.setAttribute("SESSION_username", name);
            request.getSession().setAttribute("login_manager", login_manager);
            request.getRequestDispatcher("/ManagerQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/root-login.jsp").forward(request, response);
        }
    }

    protected void ManagerRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // 2、将用户名和密码封装到Manager的对象中
        Manager manager = new Manager();
        manager.setE_name(name);
        manager.setE_password(password);
        manager.setE_identity("teacher");
        manager.setE_email(email);

        // 3、通过调用业务层的代码来进行用户的注册
        boolean register_manager = new ManagerDao().insertManager(manager);
        // 4、根据结果进行页面跳转
        if (register_manager) {//注册成功跳转到登录页面  重定向
            response.sendRedirect("/root-login.jsp");
        } else {//注册失败就返回注册页面  请求转发 /代表web
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    protected void ManagerLoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 1、接收请求的用户名和密码 根据key来获取
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 2、将用户名和密码封装到E_manager的对象中
        Manager manager = new Manager();
        manager.setE_name(name);
        manager.setE_password(password);

        PrintWriter pw = response.getWriter();

        if (!managerService.queryManagerByName(name)) {
            pw.write("1"); //用户不存在
        } else if (managerService.managerLogin(manager) == null) {
            pw.write("2"); //用户名或密码错误
        } else {
            pw.write("3"); //验证成功
        }
        pw.flush();
        pw.close();
    }

    protected void ManagerRegisterServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 1、接收请求的用户名和密码 根据key来获取
        String email = request.getParameter("email");

        PrintWriter pw = response.getWriter();
        if (managerService.queryManagerByEmail(email)) {
            pw.write("1");
        } else if (email == null || !email.matches("([a-zA-Z0-9_-])(\\w|\\-)+@([a-zA-Z0-9])+\\.([a-zA-Z]{2,4})")) {
            pw.write("2");
        } else {
            pw.write("3");
        }
        pw.flush();
        pw.close();
    }

    protected void ManagerLogOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int flag = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit TeachingManagement?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            HttpSession session = request.getSession();
            session.removeAttribute("login_manager");
            session.removeAttribute("SESSION_username");
            session.invalidate();
            request.getRequestDispatcher("/root-login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/ManagerTables.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
