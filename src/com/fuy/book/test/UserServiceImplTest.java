package com.fuy.book.test;

import com.fuy.book.entity.User;
import com.fuy.book.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceImplTest {

    UserServiceImpl userService = new UserServiceImpl();
    @Test
    public void regisUser() {
        User user = new User(null,"夏明","123","123@QQ.com");
        userService.regisUser(user);
    }

    @Test
    public void login() {
        User user = new User(null,"夏明","123","123@QQ.com");
        System.out.println(userService.login(user));

    }

    @Test
    public void existsUser() {

        System.out.println(userService.existsUser("fuy"));
    }
}