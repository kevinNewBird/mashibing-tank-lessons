package com.mashibing.dm.prototype.demo1.source;

import com.mashibing.dm.prototype.demo1.base.impl.EnterpriseOrder;
import com.mashibing.dm.prototype.demo1.base.impl.PersonalOrder;

public class OrderBusinessClient {

    public static void main(String[] args) {
        PersonalOrder op = new PersonalOrder();
        // 设置个人订单数据
        op.setOrderNum(2925);
        op.setCustomer("张三");
        op.setProductId("P0001");

        // 保存订单
        OrderBusiness client = new OrderBusiness();
        client.saveOrder(op);
    }
}
