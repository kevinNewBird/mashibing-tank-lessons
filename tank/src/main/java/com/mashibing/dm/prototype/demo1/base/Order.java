package com.mashibing.dm.prototype.demo1.base;

/**
 * 订单接口
 */
public interface Order {

    /**
     * 获取订单的产品数量
     * @return
     */
    int getOrderNum();

    /**
     * 设置订单的产品数量
     * @param orderNum：订单产品数量
     * @return
     */
    void setOrderNum(int orderNum);

}
