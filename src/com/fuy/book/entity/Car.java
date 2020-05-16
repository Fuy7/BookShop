package com.fuy.book.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//购物车
public class Car {


    //key为商品编号 value为商品carItem
    private Map<Integer,CarItem> carItems = new LinkedHashMap<Integer,CarItem>();

    @Override
    public String toString() {
        return "Car{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", carItems=" + carItems +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;

        //遍历累加
        for (Map.Entry<Integer,CarItem>entry : carItems.entrySet()) {
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer,CarItem>entry : carItems.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CarItem> getCarItems() {
        return carItems;
    }

    public void setCarItems(Map<Integer, CarItem> carItems) {
        this.carItems = carItems;
    }

    //添加商品项
    public void addCarItem(CarItem carItem){
        //查看是否有相同的商品,区分操作
        CarItem c = this.carItems.get(carItem.getId());
        if(c==null){
            //为空就添加一个商品项
            this.carItems.put(carItem.getId(),carItem);
        }else {
            //存在同样商品
            //修改此商品项数据
            c.setCount(c.getCount()+1);
            //修改价格
            c.setTotalPrice(c.getPrice().multiply(new BigDecimal(c.getCount())));
        }
    }

    //修改商品项(根据ID)
    public void updateCarItem(Integer id){
        CarItem carItem = carItems.get(id);

    }
    //删除商品项(根据ID)
    public void deleteCarItem(Integer id){
        carItems.remove(id);
    }
    //清空购物车
    public void cleanCarItem(){
        carItems.clear();
    }
    //修改商品数(根据ID)
    public void updateCount(Integer id,Integer count){
        CarItem carItem = carItems.get(id);
        //不为空时才修改
        if(carItem!=null){
            //修改数量
            carItem.setCount(count);
            //修改总金额
            carItem.setTotalPrice(carItem.getPrice().multiply(new BigDecimal(carItem.getCount())));
        }else {
            System.out.println("此商品不存在 ");
        }
    }
}
