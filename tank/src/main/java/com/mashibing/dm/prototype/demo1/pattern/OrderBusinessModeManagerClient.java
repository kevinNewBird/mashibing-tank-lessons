package com.mashibing.dm.prototype.demo1.pattern;

import com.mashibing.dm.prototype.demo1.pattern.adapter.PersonalOrderAdapter;
import com.mashibing.dm.prototype.demo1.pattern.manger.OrderManager;

import java.util.Objects;

public class OrderBusinessModeManagerClient {

    public static void main(String[] args) {
        //  1.使用原型管理器
        PersonalOrderAdapter op = (PersonalOrderAdapter) Objects.requireNonNull(OrderManager.getOrder("Personal", PersonalOrderAdapter.class))
                .cloneOrder();

        assert op != null;
        // 2.设置个人订单数据
        op.setOrderNum(3825);
        op.setCustomer("李四");
        // TODO 假设产品是一个引用类型并要求深拷贝，那么需要在产品上也使用原型模式，即实现clone
        op.setProductId("P0002");

        // 3.保存订单
        OrderBusinessMode client = new OrderBusinessMode();
        client.saveOrder(op);
    }
}
