package com.education.listener;

import com.education.bean.User;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class LoginUserCountListener implements HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //session创建后调用
        System.out.println("sessionCreated");
        //统计信息ServletContext
        ServletContext context = se.getSession().getServletContext();

        //人数,servletContext中取
        Integer count = (Integer) context.getAttribute("count");
        if (count == null) {
            count = 0;
        } else {
            count++;
        }
        context.setAttribute("count", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //session销毁调用
        System.out.println("sessionDestroyed");
        //
        ServletContext context = se.getSession().getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        count--;
        context.setAttribute("count", count);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        //当属性添加之后触发
        //将用户信息保存到servletContext中
        String attrName = se.getName();//添加的属性名 username

        if (attrName.equals("username")) {
            String username = (String) se.getValue(); //获取添加的属性值
            //将用户信息保存
            User user = new User(username);
            HttpSession session = se.getSession();
            ServletContext context = session.getServletContext();
            Map<String, User> userMap = (Map<String, User>) context.getAttribute("users");
            if (userMap == null) {
                userMap = new HashMap<String, User>();
            }
            userMap.put(session.getId(), user);
            context.setAttribute("users", userMap);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        //当属性删除之后触发
        //将用户信息保存到servletContext中
        String attrName = se.getName();//添加的属性名 username

        if (attrName.equals("username")) {
            HttpSession session = se.getSession();
            ServletContext context = session.getServletContext();
            Map<String, User> userMap = (Map<String, User>) context.getAttribute("users");

            userMap.remove(session.getId());
            context.setAttribute("users", userMap);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        //当属性修改之后触发
    }
}
