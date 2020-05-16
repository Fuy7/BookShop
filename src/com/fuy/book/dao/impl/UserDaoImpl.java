package com.fuy.book.dao.impl;

import com.fuy.book.dao.BaseUtils;
import com.fuy.book.dao.UserDao;
import com.fuy.book.entity.User;

public class UserDaoImpl extends BaseUtils implements UserDao {
    @Override
    public User queryUserByName(String userName) {
        String sql = "select id,username,password,email from t_user where username = ?";
        User user = (User) queryForOne(User.class, sql, userName);
        return user;
    }

    @Override
    public User queryUserByNameAndPwd(String userName, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        User user = (User) queryForOne(User.class, sql, userName,password);
        return user;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,`password`,email) values(?,?,?);";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
