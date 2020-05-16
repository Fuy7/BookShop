package com.fuy.book.test;

import com.fuy.book.dao.BookDao;
import com.fuy.book.dao.impl.BookDaoImpl;
import com.fuy.book.entity.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    private BookDao dao = new BookDaoImpl();
    @Test
    public void getPageTotalCount() {
        int pageTotalCount = dao.getPageTotalCount();
        System.out.println(pageTotalCount);
    }

    @Test
    public void getItems() {
        List<Book> list = dao.getItems(1, 5);
        for (Book book : list) {
            System.out.println(book);
        }
    }
}