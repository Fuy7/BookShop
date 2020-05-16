package com.fuy.book.dao;

import com.fuy.book.entity.Order;

public interface OrderDao {

    //保存订单
    public int saveOrder(Order order);
}
