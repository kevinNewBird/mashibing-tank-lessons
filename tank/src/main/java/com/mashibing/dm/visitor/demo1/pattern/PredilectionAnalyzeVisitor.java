package com.mashibing.dm.visitor.demo1.pattern;

public class PredilectionAnalyzeVisitor implements Visitor {
    @Override
    public void visitEnterprise(EnterpriseCustomer customer) {
        System.out.printf("现在对企业客户%s进行产品偏好分析...\n", customer.getName());
    }

    @Override
    public void visitPersonal(PersonalCustomer customer) {
        System.out.printf("现在对个人客户%s进行产品偏好分析...\n", customer.getName());
    }
}
