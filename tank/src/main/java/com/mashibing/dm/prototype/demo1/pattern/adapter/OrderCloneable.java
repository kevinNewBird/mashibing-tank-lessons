package com.mashibing.dm.prototype.demo1.pattern.adapter;

import com.mashibing.dm.prototype.demo1.base.Order;

/**
 * 原型接口
 */
public interface OrderCloneable extends Order {

    /**
     * 克隆订单对象
     * @return
     */
    Object cloneOrder();

}
