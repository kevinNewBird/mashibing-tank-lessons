package com.mashibing.dm.proxy.demo2.pattern.base;

import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class Order implements OrderApi{

    /**
     * 订购的产品名称
     */
    private String productName;

    /**
     * 订购的数量
     */
    private int orderNum;

    /**
     * 创建订单的人员
     */
    private String orderUser;


    @Override
    public String getProductName() {
        return this.productName;
    }

    @Override
    public void setProductName(String productName, String operUser) {
        this.productName = productName;
    }

    @Override
    public int getOrderNum() {
        return this.orderNum;
    }

    @Override
    public void setOrderNum(int orderNum, String operUser) {
        this.orderNum = orderNum;
    }

    @Override
    public String getOrderUser() {
        return this.orderUser;
    }

    @Override
    public void setOrderUser(String orderUser, String operUser) {
        this.orderUser = orderUser;
    }
}
