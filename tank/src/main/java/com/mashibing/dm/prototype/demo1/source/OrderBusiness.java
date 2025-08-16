package com.mashibing.dm.prototype.demo1.source;

import com.mashibing.dm.prototype.demo1.base.Order;
import com.mashibing.dm.prototype.demo1.base.impl.EnterpriseOrder;
import com.mashibing.dm.prototype.demo1.base.impl.PersonalOrder;

public class OrderBusiness {

    public void saveOrder(Order order) {
        if (order == null) {
            return;
        }

        while (order.getOrderNum() > 1000) {
            // 1.定义一个表示被拆分出来的新订单对象
            Order newOrder = null;
            if (order instanceof PersonalOrder) {
                // 2.创建个人订单
                PersonalOrder p2 = new PersonalOrder();
                PersonalOrder p1 = (PersonalOrder) order;
                p2.setCustomer(p1.getCustomer());
                p2.setProductId(p1.getProductId());
                p2.setOrderNum(1000);

                newOrder = p2;
            } else if (order instanceof EnterpriseOrder) {
                EnterpriseOrder e2 = new EnterpriseOrder();
                EnterpriseOrder e1 = (EnterpriseOrder) order;
                e2.setCompany(e1.getCompany());
                e2.setProductId(e1.getProductId());
                e2.setOrderNum(1000);

                newOrder = e2;
            } else {
                throw new IllegalArgumentException("Unknown order type: " + order.getClass());
            }

            // 3.原来的订单保留，数量减少
            order.setOrderNum(order.getOrderNum()-1000);

            // 4.业务功能处理
            System.out.println("拆分生成订单 === >" + newOrder);
        }

        // 5.订单剩余部分
        System.out.println("订单 === >" + order);
    }
}
