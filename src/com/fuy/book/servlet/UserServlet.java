package com.fuy.book.servlet;

import com.fuy.book.entity.User;
import com.fuy.book.service.UserService;
import com.fuy.book.service.impl.UserServiceImpl;
import com.fuy.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private User user;


    //调用登录方法
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);

        user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //进行校验
        User u = userService.login(this.user);
        if(u==null){
            System.out.println("登录失败");
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }else {
            //登录成功
            //将用户信息保存到session域中
            req.getSession().setAttribute("user",user);
            //请求转发
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }
    //注销用户
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session中关于用户的信息
        //req.getSession().invalidate();
        req.getSession().setAttribute("user","");
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    //调用注册方法
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码
/*        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");*/
        // 获取Session中的验证码(key为KAPTCHA_SESSION_KEY,在jar包中定义的)
        //session中的验证码是由谷歌提供的jar在显示出验证码后存入的(图片地址就是请求jar中servlet的地址)
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除Session中的验证码(防止表单重复提交)
        //下次提交session域中也不会在有验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repwd = req.getParameter("repwd");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //校验验证码
        if(token != null && token.equalsIgnoreCase(code)){
            //检验用户名是否可用(不重复)
            if(userService.existsUser(username)){
                req.setAttribute("msg","用户名不可用");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                System.out.println("用户名不可用(重复)");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
            else {
                //进行注册
                user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
                user = new User(username,password,email);
                userService.regisUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);

            }

        }else {
            //验证码错误请求转发到注册页面
            System.out.println("验证码错误");
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }
}
