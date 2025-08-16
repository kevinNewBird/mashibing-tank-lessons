package com.mashibing.dm.prototype.demo1.pattern;

import com.mashibing.dm.prototype.demo1.base.Order;
import com.mashibing.dm.prototype.demo1.pattern.adapter.OrderCloneable;

public class OrderBusinessMode {

    public void saveOrder(OrderCloneable order) {
        if (order == null) {
            return;
        }

        while (order.getOrderNum() > 1000) {
            // 1.定义一个表示被拆分出来的新订单对象
            Order newOrder = (Order) order.cloneOrder();
            newOrder.setOrderNum(1000);

            // 2.原来的订单保留，数量减少
            order.setOrderNum(order.getOrderNum()-1000);
            // 3.业务功能处理
            System.out.println("拆分生成订单 === >" + newOrder);
        }

        // 3.订单剩余部分
        System.out.println("订单 === >" + order);
    }
}
