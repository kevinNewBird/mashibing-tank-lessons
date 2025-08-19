package com.mashibing.dm.proxy.demo2.pattern.base;

import org.apache.commons.lang.StringUtils;

public class OrderProxy implements OrderApi {
    // 持有被代理的具体的目标对象
    private final Order realOrder;

    public OrderProxy(Order realOrder) {
        this.realOrder = realOrder;
    }

    @Override
    public String getProductName() {
        return realOrder.getProductName();
    }

    @Override
    public void setProductName(String productName, String operUser) {
        // 控制访问权限，只有创建订单的人员才能够修改
        if (StringUtils.isNotBlank(operUser) && StringUtils.equals(operUser, realOrder.getOrderUser())) {
            realOrder.setProductName(productName, operUser);
        } else {
            System.out.printf("对不起%s，您无权修改订单中的产品名称。\n", operUser);
        }
    }

    @Override
    public int getOrderNum() {
        return realOrder.getOrderNum();
    }

    @Override
    public void setOrderNum(int orderNum, String operUser) {
        // 控制访问权限，只有创建订单的人员才能够修改
        if (StringUtils.isNotBlank(operUser) && StringUtils.equals(operUser, realOrder.getOrderUser())) {
            realOrder.setOrderNum(orderNum, operUser);
        } else {
            System.out.printf("对不起%s，您无权修改订单中的订购数量。\n", operUser);
        }
    }

    @Override
    public String getOrderUser() {
        return realOrder.getOrderUser();
    }

    @Override
    public void setOrderUser(String orderUser, String operUser) {
        // 控制访问权限，只有创建订单的人员才能够修改
        if (StringUtils.isNotBlank(operUser) && StringUtils.equals(operUser, realOrder.getOrderUser())) {
            realOrder.setOrderUser(orderUser, operUser);
        } else {
            System.out.printf("对不起%s，您无权修改订单中的订购人。\n", operUser);
        }
    }

    @Override
    public String toString() {
        return realOrder.toString();
    }
}
