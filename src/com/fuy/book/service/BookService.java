package com.fuy.book.service;

import com.fuy.book.entity.Book;
import com.fuy.book.entity.Page;

import java.util.List;

public interface BookService {
    //添加图书
    public int addBook(Book book);

    //删除图书
    public int deleteBook(Integer id);

    //修改图书
    public int updataBook(Book book);

    //根据IP查找
    public Book getBookByID(Integer id);

    //查询所有图书
    public List<Book> getAllBook();

    //获取Page对象进行分页
    public Page<Book> page(Integer pageNo, Integer pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
