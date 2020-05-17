package com.fuy.book.test;

import com.fuy.book.entity.Car;
import com.fuy.book.entity.CarItem;
import com.fuy.book.service.OrderService;
import com.fuy.book.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    private OrderService service = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Car cart = new Car();

        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCarItem(new CarItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );

    }
}