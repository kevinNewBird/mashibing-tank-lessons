package com.mashibing.dm.visitor.demo1.pattern;

public interface Visitor {

    /**
     * 访问企业客户
     *
     * @param customer
     */
    void visitEnterprise(EnterpriseCustomer customer);

    /**
     * 访问个人客户
     *
     * @param customer
     */
    void visitPersonal(PersonalCustomer customer);
}
