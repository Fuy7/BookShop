package com.fuy.book.dao.impl;

import com.fuy.book.dao.BaseUtils;
import com.fuy.book.dao.BookDao;
import com.fuy.book.entity.Book;
import com.fuy.book.entity.Page;

import java.util.List;

public class BookDaoImpl extends BaseUtils implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) " +
                "values(? , ? , ? , ? , ? , ?)";
        int update = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return update;

    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from t_book where id = ?";
        int update = update(sql, id);
        return update;
    }

    @Override
    public int updataBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(),book.getId());
    }

    @Override
    public Book getBookByID(Integer id) {
        String sql  = "select id,name,author,price,sales,stock,img_path from t_book where id = ?";
        return (Book) queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> getAllBook() {
        String sql  = "select id,name,author,price,sales,stock,img_path imgPath from t_book";
        List<Book> list = (List<Book>) queryForList(Book.class, sql);
        return list;
    }

    @Override
    public int getPageTotalCount() {
        //select count (*)
        String sql = "select count(*) from t_book";
        Number number = (Number) queryForSingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<Book> getItems(Integer pageNo, Integer pageSize) {
        //select * from 表名 limit begin , pageSize
        //begin可以由公式求得: (pageNo-1) x pageSize;
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book limit ? , ?";
        int begin = (pageNo-1)* pageSize;
        List<Book> list = (List<Book>) queryForList(Book.class, sql, begin, pageSize);
        return list;
    }

    @Override
    public int getPageTotalCountByPrice(int min,int max) {
        String sql = "select count(*) from t_book where price between ? and ? ";
        Number number = (Number) queryForSingleValue(sql,min,max);
        return number.intValue();
    }

    @Override
    public List<Book> getItemsByPrice(int pageNo, int pageSize, int min, int max) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book " +
                "where price between ? and ? order by price limit ? , ? ";
        int begin = (pageNo-1)* pageSize;
        List<Book> list = (List<Book>) queryForList(Book.class, sql, min,max,begin, pageSize);
        return list;
    }
}
