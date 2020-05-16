package com.fuy.book.service.impl;


import com.fuy.book.dao.BookDao;

import com.fuy.book.dao.impl.BookDaoImpl;

import com.fuy.book.entity.Book;
import com.fuy.book.entity.Page;
import com.fuy.book.service.BookService;
import com.fuy.book.utils.WebUtils;


import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao dao = new BookDaoImpl();


    @Override
    public int addBook(Book book) {
        return dao.addBook(book);
    }

    @Override
    public int deleteBook(Integer id) {
        return dao.deleteBook(id);
    }

    @Override
    public int updataBook(Book book) {
        return dao.updataBook(book);
    }

    @Override
    public Book getBookByID(Integer id) {
        return dao.getBookByID(id);
    }

    @Override
    public List<Book> getAllBook() {
        return dao.getAllBook();
    }

    //pageNo 前前页数, pageSize 每页显示的数据数
    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {


        //获取总数据量
        int pageTotalCount = dao.getPageTotalCount();

        List<Book> items = dao.getItems(pageNo, pageSize);

        //pageTotal 总页数 = 总数据 / 每页显示的数据
        int pageTotal = pageTotalCount / pageSize;

        //后端非法校验
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }
        if(pageTotalCount % pageSize>0){
            pageTotal++;
        }
        //封装到Page对象中
        return new Page<Book>(pageNo,pageTotal,pageTotalCount,pageSize,items);
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        //获取当前页数据
        List<Book> books = dao.getItemsByPrice(pageNo,pageSize,min,max);
        //获取总数据量
        int pageTotalCount = dao.getPageTotalCountByPrice(min,max);
        //pageTotal 总页数 = 总数据 / 每页显示的数据
        int pageTotal = pageTotalCount / pageSize;
        Page<Book> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setPageTotal(pageTotal);
        page.setPageTotalCount(pageTotalCount);
        page.setItems(books);
        return page;
    }
}
