package com.fuy.book.filter;

import com.fuy.book.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //进行拦截后的处理
        HttpServletRequest req = (HttpServletRequest) servletRequest;   //进行强转
        HttpServletResponse resp = (HttpServletResponse) servletResponse;   //进行强转

        //判断是否登录
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){
            //没登录就跳转到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
        else {
            // 让程序继续往下访问用户的目标资源
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
