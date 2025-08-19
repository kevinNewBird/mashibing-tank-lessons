package com.mashibing.dm.proxy.demo2.pattern;

import com.mashibing.dm.proxy.demo2.pattern.base.Order;
import com.mashibing.dm.proxy.demo2.pattern.base.OrderProxy;

public class OrderProxyClient {

    public static void main(String[] args) {
        // 1. 张三创建一个订单
        OrderProxy order = new OrderProxy(new Order("设计模式", 100, "张三"));

        // 2.李四想要修改，那就回报错
        order.setOrderNum(123, "李四");

        // 3.张三修改就不会有问题
        order.setOrderNum(123,"张三");

        // 4.再次输出order
        System.out.println("张三修改后，订单记录："+ order);


        /**
         * 对不起李四，您无权修改订单中的订购数量。
         * 张三修改后，订单记录：Order(productName=设计模式, orderNum=123, orderUser=张三)
         */
    }
}
