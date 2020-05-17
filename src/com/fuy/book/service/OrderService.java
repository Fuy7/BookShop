package com.fuy.book.service;

import com.fuy.book.entity.Car;
import com.fuy.book.entity.Order;

public interface OrderService {

    //创建订单
    public String createOrder(Car car,Integer userId);
}
