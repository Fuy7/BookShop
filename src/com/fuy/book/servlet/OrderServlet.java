package com.fuy.book.servlet;

import com.fuy.book.entity.Car;
import com.fuy.book.entity.User;
import com.fuy.book.service.OrderService;
import com.fuy.book.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    //创建订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session域中的购物车对象
        Car car = (Car) req.getSession().getAttribute("car");
        //获取session域中用户的ID
        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user.getId());
        if(user.getId()==null){
            //请求转发到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        //调用service生成订单
        String orderId = orderService.createOrder(car, user.getId());
        //将订单号存储到session域中
        req.getSession().setAttribute("orderId",orderId);
        System.out.println(orderId);
        //重定向到订单创建成功页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
