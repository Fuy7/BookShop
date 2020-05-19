package com.fuy.book.dao;

import com.fuy.book.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseUtils {

    //使用DBUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();
    //update()方法用来执行: Insert \Update \Delete语句
    public int update(String sql,Object ... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return  queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            //捕获异常后需要向外抛出,以此判断是否回滚
            throw new RuntimeException(e);
        }
    }
    //查询一条数据
    //Class<T> type 指定返回的数据类型
    public <T>Object queryForOne(Class<T> type,String sql,Object ... args){

        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        }  catch (SQLException e) {
            e.printStackTrace();
            //捕获异常后需要向外抛出,以此判断是否回滚
            throw new RuntimeException(e);
        }
    }
    //查询多条数据
    //Class<T> type 指定返回的数据类型
    public <T>Object queryForList(Class<T> type,String sql,Object ... args){

        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        }  catch (SQLException e) {
            e.printStackTrace();
            //捕获异常后需要向外抛出,以此判断是否回滚
            throw new RuntimeException(e);
        }
    }
    //查询一行一列方法
    public Object queryForSingleValue(String sql,Object ... args){

        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        }  catch (SQLException e) {
            e.printStackTrace();
            //捕获异常后需要向外抛出,以此判断是否回滚
            throw new RuntimeException(e);
        }
    }
}
