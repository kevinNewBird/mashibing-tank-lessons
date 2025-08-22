package com.mashibing.dm.visitor.demo1.pattern;

public class ServiceRequestVisitor implements Visitor{

    @Override
    public void visitEnterprise(EnterpriseCustomer customer) {
        System.out.println(customer.getName() + "企业提出服务请求！");
    }

    @Override
    public void visitPersonal(PersonalCustomer customer) {
        System.out.printf("客户%s提出服务请求！\n", customer.getName());
    }
}
