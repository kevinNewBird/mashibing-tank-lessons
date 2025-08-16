package com.mashibing.dm.prototype.demo1.base.impl;

import com.mashibing.dm.prototype.demo1.base.Order;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 企业订单
 */
@Data
public class EnterpriseOrder implements Order {
    /**
     * 产品id
     * 说明：一个产品一个订单可以很好实现同一个商家的不同产品的订单修改和取消等颗粒化操作
     */
    private String productId;
    /**
     * 公司
     */
    private String company;
    /**
     * 产品数量
     */
    private int orderNum;

    @Override
    public String toString() {
        String format = "本企业订单的订购企业是%s，订购产品是%s，订购数量为%s";
        return String.format(format, company, productId, orderNum);
    }
}
