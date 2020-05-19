package com.fuy.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    //创建数据库连接池的对象
    private static DruidDataSource dataSource;
    //创建ThreadLocal对象
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    //静态初始化
    static {
        try {
            Properties properties = new Properties();
            //读取配置文件的流
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载s数据
            properties.load(inputStream );
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //获取熟数据库连接池中的连接
    public static Connection getConnection(){
        //从ThreadLocal中获取
        Connection connection = conns.get();
        //未将连接放置ThreadLocal
        if(connection == null){
            try {
                //从连接池中获取出一个连接
                connection = dataSource.getConnection();
                //存储到ThreadLocal中
                conns.set(connection);
                //设置为手动管理事务
                connection.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    //提交事务并关闭连接
    public static void CommitAndClose(){
        //从ThreadLocal获取连接
        Connection connection = conns.get();
        //说明使用过连接
        if(connection != null){
            //提交事务
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();  //一定一定要手动关闭ThreadLocal,防止内存泄漏
    }

    //回滚事务并关闭连接
    public static void rollbackAndClose(){
        //从ThreadLocal获取连接
        Connection connection = conns.get();
        //说明使用过连接
        if(connection != null){
            //提交事务
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();  //一定一定要手动关闭ThreadLocal,防止内存泄漏
    }

}
