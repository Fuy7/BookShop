package com.fuy.book.servlet;

import com.fuy.book.entity.Book;
import com.fuy.book.entity.Car;
import com.fuy.book.entity.CarItem;
import com.fuy.book.service.BookService;
import com.fuy.book.service.impl.BookServiceImpl;
import com.fuy.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    //商品加入购物车
    protected void addToCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        Integer id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用service,使用ID获取图书信息
        Book book = bookService.getBookByID(id);
        //将商品信息封装为商品信息
        CarItem carItem = new CarItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //创建购物车并存入到session中
        Car car = (Car) req.getSession().getAttribute("car");
        if(car==null){
            //不存在购物车就创建一个
            car = new Car();
            req.getSession().setAttribute("car", car);
        }
        //存在购物车
        //添加到购物车
        car.addCarItem(carItem);
        //将添加的商品信息添加到session域中
        req.getSession().setAttribute("carItem",carItem);
        //重定向到原来商品列表页面（请求头中Referer属性记录了原来的地址）
        resp.sendRedirect(req.getHeader("Referer"));
    }
    //删除商品项
    protected void deleteCarItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要删除商品项的ID
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取session域中的car对象
        Car car = (Car) req.getSession().getAttribute("car");
        if(car==null){
            //不存在购物车就创建一个
            car = new Car();
            req.getSession().setAttribute("car", car);
        }
        //调用car中的删除方法
        car.deleteCarItem(id);
        //重定向回到购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    //清空购物车
    protected void cleanCarItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session域中的car对象
        Car car = (Car) req.getSession().getAttribute("car");
        if(car==null){
            //不存在购物车就创建一个
            car = new Car();
            req.getSession().setAttribute("car", car);
        }
        //调用car中的清空购物车方法
        car.cleanCarItem();
        //回到购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    //修改商品数量
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取修改的商品ID
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取新修改的商品数量
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        //获取session域中的car对象
        Car car = (Car) req.getSession().getAttribute("car");
        if(car==null){
            //不存在购物车就创建一个
            car = new Car();
            req.getSession().setAttribute("car", car);
        }
        //调用car中的修改数量方法
        car.updateCount(id,count);
        //回到购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    //使用ajax将商品添加到购物车
    protected void ajaxAddToCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品ID
        Integer id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用service,使用ID获取图书信息
        Book book = bookService.getBookByID(id);
        //将book对象封装为商品信息
        CarItem carItem = new CarItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //创建购物车并存入到session中
        Car car = (Car) req.getSession().getAttribute("car");
        if(car==null){
            //不存在购物车就创建一个
            car = new Car();
            req.getSession().setAttribute("car", car);
        }
        //向购物车中添加商品
        car.addCarItem(carItem);
        //将添加的商品信息添加到session域中
        req.getSession().setAttribute("lastCarItem",carItem.getName());
        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",car.getTotalCount());
        resultMap.put("lastCarItemName",carItem.getName());

        //将resultMap转为JSON字符串
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
