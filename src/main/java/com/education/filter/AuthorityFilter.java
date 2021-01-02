package com.education.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 管理员问权限过滤器
 *
 */
//urlPatterns 过滤器拦截的url
@WebFilter(
        urlPatterns = { "/ManagerTables.jsp","/LessonTables.jsp"},
        initParams = { @WebInitParam(name = "login", value = "login.jsp"),
                        @WebInitParam(name="register",value = "register.jsp")},
        dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })//配置拦截的类型，可配置多个,FORWARD转发的, INCLUDE包含在页面的,REQUEST请求的,

public class AuthorityFilter implements Filter {
    private String login = "login.jsp";

    public AuthorityFilter() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取当请求被拦截时转向的页面
        login = filterConfig.getInitParameter("login");
        if (null == login) {
            login = "login.jsp";
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //业务逻辑
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        // 判断被拦截的请求用户是否处于登录状态
        if (session.getAttribute("SESSION_username") == null) {
            // 获取被拦截的请求地址及参数
            String requestPath = req.getRequestURI();
            if (req.getQueryString() != null) {
                requestPath += "?" + req.getQueryString();
            }
            // 将请求地址保存到request对象中转发到登录页面
            req.setAttribute("requestPath", requestPath);
            servletRequest.getRequestDispatcher( "/" + login)
                    .forward(servletRequest, servletResponse);
            return;
        } else {
            //将当前拦截的请求放行，让请求继续访问他要访问的资源
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        this.login = null;
    }
}
