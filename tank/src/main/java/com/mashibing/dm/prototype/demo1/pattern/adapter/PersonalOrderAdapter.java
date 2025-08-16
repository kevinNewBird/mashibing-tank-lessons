package com.mashibing.dm.prototype.demo1.pattern.adapter;

import com.mashibing.dm.prototype.demo1.base.impl.PersonalOrder;

public class PersonalOrderAdapter extends PersonalOrder implements OrderCloneable {

    @Override
    public Object cloneOrder() {
        PersonalOrderAdapter op = new PersonalOrderAdapter();
        // 设置个人订单数据
        op.setOrderNum(this.getOrderNum());
        op.setCustomer(this.getCustomer());
        op.setProductId(this.getProductId());
        return op;
    }
}
