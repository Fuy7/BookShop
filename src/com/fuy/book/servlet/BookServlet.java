package com.fuy.book.servlet;

import com.fuy.book.entity.Book;
import com.fuy.book.entity.Page;
import com.fuy.book.service.BookService;

import com.fuy.book.service.impl.BookServiceImpl;
import com.fuy.book.utils.WebUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取图书信息列表
        List<Book> bookList = bookService.getAllBook();
        //存储到request域中
        req.setAttribute("bookList",bookList);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求信息封装成Book对象
        Map<String, String[]> map = req.getParameterMap();
        Book book = WebUtils.copyParamToBean(map, new Book());
        //调用service添加图书
        bookService.addBook(book);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo")+"&pageSize=4");

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取请求信息
        String strid = req.getParameter("id");

        int id = Integer.parseInt(strid);
        //调用service
        bookService.deleteBook(id);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo")+"&pageSize=4");
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // //获取请求信息封装成Book对象
        Map<String, String[]> map = req.getParameterMap();
        Book book = WebUtils.copyParamToBean(map, new Book());

        //调用service
        int i = bookService.updataBook(book);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo")+"&pageSize=4");
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求信息
        String strid = req.getParameter("id");
        int id = Integer.parseInt(strid);

        //调用service
        Book book = bookService.getBookByID(id);
        req.setAttribute("book",book);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp?pageNo="+req.getParameter("pageNo")+"&pageSize=4").forward(req,resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //获取请求的参数 pageNo 和 pageSize.
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        //
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
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }



}
