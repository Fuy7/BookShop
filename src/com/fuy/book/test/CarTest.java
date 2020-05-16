package com.fuy.book.test;

import com.fuy.book.entity.Car;
import com.fuy.book.entity.CarItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void addCarItem() {
        Car cart = new Car();

        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCarItem(new CarItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        System.out.println(cart);
    }

    @Test
    public void deleteCarItem() {
    }

    @Test
    public void cleanCarItem() {
        Car cart = new Car();

        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCarItem(new CarItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));


        //cart.deleteCarItem(1);

        cart.cleanCarItem();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Car cart = new Car();

        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addCarItem(new CarItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));


        //cart.deleteCarItem(1);

        cart.cleanCarItem();

        cart.addCarItem(new CarItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));

        cart.updateCount(1, 10);


        System.out.println(cart);
    }
}