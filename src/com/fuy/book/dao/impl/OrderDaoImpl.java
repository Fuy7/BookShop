package com.fuy.book.dao.impl;

import com.fuy.book.dao.BaseUtils;
import com.fuy.book.dao.OrderDao;
import com.fuy.book.entity.Order;

public class OrderDaoImpl extends BaseUtils implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
