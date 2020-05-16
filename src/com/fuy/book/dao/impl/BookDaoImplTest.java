package com.fuy.book.dao.impl;

import com.fuy.book.dao.BookDao;
import com.fuy.book.entity.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    private BookDao dao = new BookDaoImpl();

    @Test
    public void addBook() {
        int i = dao.addBook(new Book(null, "javaScript高级编程", "fuy", new BigDecimal(69.15), 210, 810, "static/img/default.jpg"));
        System.out.println(i);
    }

    @Test
    public void deleteBook() {
        dao.deleteBook(21);
    }

    @Test
    public void updataBook() {
        int i = dao.updataBook(new Book(22, "test", "test", new BigDecimal(69.15), 210, 810, "static/img/default.jpg"));
        System.out.println(i);
    }

    @Test
    public void getBookByID() {
        System.out.println(dao.getBookByID(22));
    }

    @Test
    public void getAllBook() {
        System.out.println(dao.getAllBook());
    }

    @Test
    public void getPageTotalCountByPrice() {
        int pageTotalCountByPrice = dao.getPageTotalCountByPrice(50, 90);
        System.out.println(pageTotalCountByPrice);
    }

    @Test



    public void getItemsByPrice() {
        List<Book> list = dao.getItemsByPrice(1, 4, 50, 90);
        for (Book book : list) {
            System.out.println(book);
        }
    }
}