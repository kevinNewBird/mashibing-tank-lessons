package com.mashibing.dm.visitor.demo1.source;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerClient {

    /**
     * 存在的问题点：
     *
     * @param args
     */
    public static void main(String[] args) {
        Collection<Customer> customers = prepareTestData();
        for (Customer customer : customers) {
            // 进行偏好分析
            customer.predilectionAnalyze();
            // 进行价值分析
            customer.worthAnalyze();
        }

        /**
         * 现在对企业客户ABC集团进行产品偏好分析...
         * 现在对企业客户ABC集团进行价值分析...
         * 现在对企业客户CDE集团进行产品偏好分析...
         * 现在对企业客户CDE集团进行价值分析...
         * 现在对个人客户张三进行产品偏好分析...
         * 现在对个人客户张三进行价值分析...
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
