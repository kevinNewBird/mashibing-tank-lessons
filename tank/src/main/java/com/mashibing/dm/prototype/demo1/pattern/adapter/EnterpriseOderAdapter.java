package com.mashibing.dm.prototype.demo1.pattern.adapter;

import com.mashibing.dm.prototype.demo1.base.impl.EnterpriseOrder;

/**
 * 为了不破坏基础类接口，使用adapter对基础类进行封装
 */
public class EnterpriseOderAdapter extends EnterpriseOrder implements OrderCloneable {

    @Override
    public Object cloneOrder() {
        EnterpriseOderAdapter ep = new EnterpriseOderAdapter();
        // 设置企业订单数据
        ep.setOrderNum(this.getOrderNum());
        ep.setCompany(this.getCompany());
        ep.setProductId(this.getProductId());
        return ep;
    }
}
