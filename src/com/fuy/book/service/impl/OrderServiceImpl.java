package com.fuy.book.service.impl;

import com.fuy.book.dao.BookDao;
import com.fuy.book.dao.OrderDao;
import com.fuy.book.dao.OrderItemDao;
import com.fuy.book.dao.impl.BookDaoImpl;
import com.fuy.book.dao.impl.OrderDaoImpl;
import com.fuy.book.dao.impl.OrderItemDaoImpl;
import com.fuy.book.entity.*;
import com.fuy.book.service.OrderService;
import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao itemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Car car, Integer userId) {

        //先保存订单
        String orderId = System.currentTimeMillis()+""+userId; //订单号(唯一标识) 时间戳唯一
        //创建订单对象
        Order order = new Order(orderId,new Date(),car.getTotalPrice(),0,userId);
        //保存订单
        int i = orderDao.saveOrder(order);
        //保存成功
        if(i>0){
            //遍历购物车中的每一个商品转化为订单项
            Map<Integer, CarItem> carItems = car.getCarItems();
            for (Integer integer : carItems.keySet()) {
                CarItem carItem = carItems.get(integer);
                //创建订单项
                OrderItem orderItem = new OrderItem(null,carItem.getName(),carItem.getCount(),carItem.getPrice(),carItem.getTotalPrice(),orderId);
                //将订单项保存到订单中
                itemDao.saveOrderItem(orderItem);
                int j = 1/0;
                //获取商品对应的图书
                Book book = bookDao.getBookByID(carItem.getId());
                //修改此书的库存
                book.setStock(book.getStock()-carItem.getCount());
                //修改销量
                book.setSales(book.getSales()+carItem.getCount());
                bookDao.updataBook(book);

            }
            //清空购物车
            car.cleanCarItem();
        }else {
            return null;
        }
        //返回订单号
        return orderId;
    }


}
