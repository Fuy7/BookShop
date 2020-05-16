package com.fuy.book.service.impl;

import com.fuy.book.entity.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    private BookServiceImpl bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "testService", "testService", new BigDecimal(69.15), 210, 810, "static/img/default.jpg"));
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(22);
    }

    @Test
    public void updataBook() {
        bookService.updataBook(new Book(23, "testService111", "testService", new BigDecimal(69.15), 210, 810, "static/img/default.jpg"));
    }

    @Test
    public void getBookByID() {
        System.out.println(bookService.getBookByID(2));
    }

    @Test
    public void getAllBook() {
        System.out.println(bookService.getAllBook());
    }
}