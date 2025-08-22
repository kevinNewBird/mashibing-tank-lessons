package com.mashibing.dm.visitor.demo1.source;

import lombok.Data;

/**
 * 个人客户
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
    public void serviceRequest() {
        System.out.printf("客户%s提出服务请求！\n", this.getName());
    }

    /**
     * START======扩展功能：无设计模式，通过接口的方式
     */

    @Override
    public void predilectionAnalyze() {
        System.out.printf("现在对个人客户%s进行产品偏好分析...\n", this.getName());
    }

    @Override
    public void worthAnalyze() {
        System.out.printf("现在对个人客户%s进行价值分析...\n", this.getName());
    }
}
