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
    // ����ҵ���Ķ���
    ManagerService managerService = new ManagerServiceImpl();

    // ������� ��Ӧ����
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ��ȡuri��ȥ�жϾ�����ʵĲ���
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
        // ����һ��ҳ�����
        int pageindex = 1;
        String pageNo = request.getParameter("pageNo");
        if (pageNo != null) {
            // ��������ת��
            pageindex = Integer.parseInt(pageNo);
        }
        PageModel page = new PageModel(pageindex, ManagerDao.queryManagerCount());
        // ͨ������ҵ���Ĵ������������ݿ�õ���������
        List<Manager> list = managerService.queryManagerByPage(page);
        // 1�������ݱ�����ĳ�������������� ������session���Ự������
        // ����pagemodel�����ݵ���������
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("ManagerList", list);
        // 2����תҳ��
        request.getRequestDispatcher("manager/ManagerTables.jsp").forward(request, response);
    }

    protected void ManagerQueryName(HttpServletRequest request, HttpServletResponse response)
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
        String name = request.getParameter("e_name");
        // ����pageģ�͵Ķ���
        PageModel page = new PageModel(pageindex, ManagerDao.queryManagerByNameCount(name));
        // 2��ͨ������ҵ���Ĵ��������в�ѯ
        List<Manager> list = managerService.queryManagerByName(name, page);
        // 3�������ݱ�����ĳ�������������� ������session���Ự������
        request.setAttribute("ManagerQueryName", name);
        // ����pagemodel�����ݵ���������
        request.setAttribute("pageInfo", page);
        request.getSession().setAttribute("ManagerList", list);
        // 4����תҳ��
        request.getRequestDispatcher("manager/ManagerTables.jsp").forward(request, response);
    }

    protected void ManagerUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1������������û��������� ����key����ȡ
        String e_id = request.getParameter("e_id");
        int id = Integer.parseInt(e_id);
        String name = request.getParameter("e_name");
        String password = request.getParameter("e_password");
        String identity = request.getParameter("e_identity");
        String email = request.getParameter("e_email");
        // 2�����û����������װ��Manager�Ķ�����
        Manager manager = new Manager(id, name, password, identity, email);
        // 3��ͨ������ҵ���Ĵ����������û����޸�
        boolean isok = managerService.updateManager(manager);
        // 4�����ݽ������ҳ����ת
        if (isok) {// �ɹ�
            request.getRequestDispatcher("/ManagerQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("/ManagerToUpdate?e_id=" + id).forward(request, response);
        }
    }

    protected void ManagerToUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1�����������id
        String e_id = request.getParameter("e_id");
        int id = Integer.parseInt(e_id);
        // 2��ͨ������ҵ���Ĵ����������û��Ĳ�ѯ
        Manager updateManager = managerService.queryManagerById(id);
        // �����ݱ�������������
        request.setAttribute("UpdateManager", updateManager);
        // 3�����ݽ������ҳ����ת
        request.getRequestDispatcher("manager/updateManager.jsp").forward(request, response);
    }

    protected void ManagerDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int flag = JOptionPane.showConfirmDialog(null, "��ȷ��ɾ���ù���Ա��Ϣ?", "��ʾ", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION) {
            // 1�����������id
            String e_id = request.getParameter("e_id");
            int id = Integer.parseInt(e_id);
            // 2��ͨ������ҵ���Ĵ����������û���ɾ��
            boolean isok = managerService.deleteManager(id);
            PrintWriter out = response.getWriter();
            if (isok) {
                out.println("<script>alert('ɾ���ɹ�!��')</script>");
            } else {
                out.println("<script>alert('ɾ��ʧ��!��')</script>");
            }
        }
        // 3�����ݽ������ҳ����ת
        request.getRequestDispatcher("/ManagerQuery").forward(request, response);
    }

    protected void ManagerInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1������������û��������� ����key����ȡ
        String name = request.getParameter("e_name");
        String password = request.getParameter("e_password");
        // 2�����û����������װ��Manager�Ķ�����
        Manager manager = new Manager();
        manager.setE_name(name);
        manager.setE_password(password);
        // 3��ͨ������ҵ���Ĵ����������û������
        boolean isok = managerService.insertManager(manager);
        // 4�����ݽ������ҳ����ת
        if (isok) {// �ɹ�
            request.getRequestDispatcher("/ManagerQuery").forward(request, response);
        } else {
            request.getRequestDispatcher("manager/addManager.jsp").forward(request, response);
        }
    }

    protected void ManagerLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1������������û��������� ����key����ȡ
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 2�����û����������װ��Manager�Ķ�����
        Manager manager = new Manager();
        manager.setE_name(name);
        manager.setE_password(password);
        // 3��ͨ������ҵ���Ĵ����������û��ĵ�¼У��
        Manager login_manager = managerService.managerLogin(manager);
        // 4�����ݽ������ҳ����ת
        if (login_manager != null) {// �в�ѯ�����û����ɹ�
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
        // 1������������û��������� ����key����ȡ
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // 2�����û����������װ��Manager�Ķ�����
        Manager manager = new Manager();
        manager.setE_name(name);
        manager.setE_password(password);
        manager.setE_identity("teacher");
        manager.setE_email(email);

        // 3��ͨ������ҵ���Ĵ����������û���ע��
        boolean register_manager = new ManagerDao().insertManager(manager);
        // 4�����ݽ������ҳ����ת
        if (register_manager) {//ע��ɹ���ת����¼ҳ��  �ض���
            response.sendRedirect("/root-login.jsp");
        } else {//ע��ʧ�ܾͷ���ע��ҳ��  ����ת�� /����web
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    protected void ManagerLoginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 1������������û��������� ����key����ȡ
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        // 2�����û����������װ��E_manager�Ķ�����
        Manager manager = new Manager();
        manager.setE_name(name);
        manager.setE_password(password);

        PrintWriter pw = response.getWriter();

        if (!managerService.queryManagerByName(name)) {
            pw.write("1"); //�û�������
        } else if (managerService.managerLogin(manager) == null) {
            pw.write("2"); //�û������������
        } else {
            pw.write("3"); //��֤�ɹ�
        }
        pw.flush();
        pw.close();
    }

    protected void ManagerRegisterServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 1������������û��������� ����key����ȡ
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
