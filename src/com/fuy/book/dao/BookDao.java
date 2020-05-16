package com.fuy.book.dao;

import com.fuy.book.entity.Book;

import java.util.List;

public interface BookDao {
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

    //求总记录数
    int getPageTotalCount();

    //求当前页数据
    List<Book> getItems(Integer pageNo, Integer pageSize);

    int getPageTotalCountByPrice(int min,int max);

    List<Book> getItemsByPrice(int pageNo, int pageSize, int min, int max);
}
