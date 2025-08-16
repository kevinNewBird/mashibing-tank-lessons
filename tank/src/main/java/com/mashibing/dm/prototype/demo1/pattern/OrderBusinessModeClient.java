package com.mashibing.dm.prototype.demo1.pattern;

import com.mashibing.dm.prototype.demo1.pattern.adapter.PersonalOrderAdapter;

public class OrderBusinessModeClient {

    public static void main(String[] args) {
        // TODO 原型管理器的概念，也就是初始化时缓存原型对象在管理器中，需要时获取后克隆
        PersonalOrderAdapter op = new PersonalOrderAdapter();
        // 设置个人订单数据
        op.setOrderNum(2925);
        op.setCustomer("张三");
        // TODO 假设产品是一个引用类型并要求深拷贝，那么需要在产品上也使用原型模式，即实现clone
        op.setProductId("P0001");

        // 保存订单
        OrderBusinessMode client = new OrderBusinessMode();
        client.saveOrder(op);

    }
}
