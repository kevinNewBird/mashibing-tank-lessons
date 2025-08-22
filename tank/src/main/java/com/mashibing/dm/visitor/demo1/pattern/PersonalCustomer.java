package com.mashibing.dm.visitor.demo1.pattern;

import com.mashibing.dm.visitor.demo1.pattern.Customer;
import lombok.Data;

/**
 * 个人客户: 元素对象的具体实现
 */
@Data
public class PersonalCustomer extends Customer {

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 年龄
     */
    private int age;

    /**
     * 企业注册地址
     */
    private String address;

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPersonal(this);
    }
}

