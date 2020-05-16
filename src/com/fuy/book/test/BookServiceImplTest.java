package com.fuy.book.test;


import com.fuy.book.entity.Book;
import com.fuy.book.entity.Page;
import com.fuy.book.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;


public class BookServiceImplTest {

    private BookServiceImpl servlet = new BookServiceImpl();

    @Test
    public void page() {
        Page<Book> page = servlet.page(1, 5);
        List<Book> items = page.getItems();
        for (Book item : items) {
            System.out.println(item);
        }
    }
}