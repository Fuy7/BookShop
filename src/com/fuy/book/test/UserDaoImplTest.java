package com.fuy.book.test;

import com.fuy.book.dao.impl.UserDaoImpl;
import com.fuy.book.entity.User;
import org.junit.Test;

public class UserDaoImplTest {

    @Test
    public void queryUserByName() {
        UserDaoImpl dao = new UserDaoImpl();
        User user = dao.queryUserByName("admin");
        System.out.println(user);
    }

    @Test
    public void queryUserByNameAndPwd() {
        UserDaoImpl dao = new UserDaoImpl();
        User user = dao.queryUserByNameAndPwd("admin", "admin");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        UserDaoImpl dao = new UserDaoImpl();
        User user = new User(null,"fuy","123","fuy@QQ.com");
        int i = dao.saveUser(user);
        System.out.println(i);
    }
}