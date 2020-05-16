package com.fuy.book.service;

import com.fuy.book.entity.User;

public interface UserService {

    //- 注册用户
    public void regisUser(User user);

    //- 用户登录
    public User login(User user);

    //- 校验用户(根据用户名)
    public boolean existsUser(String username);
}
