package com.mashibing.dm.prototype.demo1.base.impl;

import com.mashibing.dm.prototype.demo1.base.Order;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 个人订单
 */
@Data
public class PersonalOrder implements Order {

    /**
     * 产品id
     * 说明：一个产品一个订单可以很好实现同一个商家的不同产品的订单修改和取消等颗粒化操作
     */
    private String productId;
    /**
     * 客户
     */
    private String customer;
    /**
     * 产品数量
     */
    private int orderNum;

    @Override
    public String toString() {
        String format = "本个人订单的订购人是%s，订购产品是%s，订购数量为%s";
        return String.format(format, customer, productId, orderNum);
    }

}
