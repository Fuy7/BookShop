package com.fuy.book.servlet;

import com.fuy.book.entity.Book;
import com.fuy.book.entity.Page;
import com.fuy.book.service.BookService;
import com.fuy.book.service.impl.BookServiceImpl;
import com.fuy.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //获取请求的参数 pageNo 和 pageSize.
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        if(pageNo==0){
            pageNo = 1;
        }
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        if(pageSize==0){
            pageSize = Page.PAGE_SIEZ;
        }
        //调用BookService.page(pageNo, pageSize) :Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
         //保存到Request域中
        req.setAttribute("page",page);
         //请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参數pageNo, pageSize, min, max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIEZ);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2、调用bookService. pageByPrice(pageNo, pageSize, min, max) :Page对象;
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        //3、保存分页对象到Reqeust域中
        req.setAttribute("page",page);
        //4、请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


}
