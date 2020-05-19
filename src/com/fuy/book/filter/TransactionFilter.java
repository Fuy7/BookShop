package com.fuy.book.filter;

import com.fuy.book.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    //统一地给所有的xxxService.xxxx()方法都统一加上try-catch()来实现事务的管理
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            //filterChain.doFilter()方法可以向下执行Servlet资源
            filterChain.doFilter(servletRequest,servletResponse);
            //无异常 提价事务
            JdbcUtils.CommitAndClose();
        }catch (Exception e){
            //有异常需要回滚事务
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
