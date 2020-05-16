package com.fuy.book.dao;

import com.fuy.book.entity.OrderItem;

public interface OrderItemDao {

    //保存订单
    public int saveOrderItem(OrderItem orderItem);
}
