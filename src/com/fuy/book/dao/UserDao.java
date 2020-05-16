package com.fuy.book.dao;

import com.fuy.book.entity.User;

public interface UserDao {

    //根据用户名查询用户信息
    public User queryUserByName(String userName);

    //根据用户名和密码查询用户信息
    public User queryUserByNameAndPwd(String userName,String password);

    //注册用户
    public int saveUser(User user);
}
