package com.fuy.book.dao.impl;

import com.fuy.book.dao.BaseUtils;
import com.fuy.book.dao.OrderItemDao;
import com.fuy.book.entity.OrderItem;


public class OrderItemDaoImpl extends BaseUtils implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
