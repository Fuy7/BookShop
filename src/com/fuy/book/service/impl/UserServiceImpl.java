package com.fuy.book.service.impl;

import com.fuy.book.dao.impl.UserDaoImpl;
import com.fuy.book.entity.User;
import com.fuy.book.service.UserService;

public class UserServiceImpl implements UserService {

    //操作数据库的对象
    private UserDaoImpl dao = new UserDaoImpl();

    //注册
    @Override
    public void regisUser(User user) {
        dao.saveUser(user);
    }

    //登录
    @Override
    public User login(User user) {
        return dao.queryUserByNameAndPwd(user.getUsername(), user.getPassword());
    }

    //检测
    @Override
    public boolean existsUser(String username) {
        User user = dao.queryUserByName(username);
        if(user == null){
            return false;
        }
        return true;
    }
}
