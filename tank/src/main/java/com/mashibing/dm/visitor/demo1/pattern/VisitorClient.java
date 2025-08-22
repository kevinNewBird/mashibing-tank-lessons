package com.mashibing.dm.visitor.demo1.pattern;

import java.util.ArrayList;
import java.util.Collection;

public class VisitorClient {

    public static void main(String[] args) {
        Collection<Customer> customers = prepareTestData();

        ServiceRequestVisitor serviceRequestVisitor = new ServiceRequestVisitor();
        PredilectionAnalyzeVisitor predilectionAnalyzeVisitor = new PredilectionAnalyzeVisitor();
        for (Customer customer : customers) {
            customer.accept(serviceRequestVisitor);
            customer.accept(predilectionAnalyzeVisitor);
        }

        /**
         * ABC集团企业提出服务请求！
         * 现在对企业客户ABC集团进行产品偏好分析...
         * CDE集团企业提出服务请求！
         * 现在对企业客户CDE集团进行产品偏好分析...
         * 客户张三提出服务请求！
         * 现在对个人客户张三进行产品偏好分析...
         */
    }

    private static Collection<Customer> prepareTestData(){
        Collection<Customer> customers = new ArrayList<>();
        // 准备测试数据
        EnterpriseCustomer cm1 = new EnterpriseCustomer();
        cm1.setName("ABC集团");
        customers.add(cm1);

        EnterpriseCustomer cm2 = new EnterpriseCustomer();
        cm2.setName("CDE集团");
        customers.add(cm2);

        PersonalCustomer pm1 = new PersonalCustomer();
        pm1.setName("张三");
        customers.add(pm1);

        return  customers;
    }
}
