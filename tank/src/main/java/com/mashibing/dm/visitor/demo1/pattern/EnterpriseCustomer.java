package com.mashibing.dm.visitor.demo1.pattern;


import com.mashibing.dm.visitor.demo1.pattern.Customer;
import lombok.Data;

/**
 * 企业客户：元素对象的具体实现
 */
@Data
public class EnterpriseCustomer extends Customer {

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 企业注册地址
     */
    private String address;

    @Override
    public void accept(Visitor visitor) {
        visitor.visitEnterprise(this);
    }
}
