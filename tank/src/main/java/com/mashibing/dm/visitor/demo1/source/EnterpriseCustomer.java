package com.mashibing.dm.visitor.demo1.source;


import lombok.Data;

/**
 * 企业客户
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
    public void serviceRequest() {
        System.out.println(this.getName() + "企业提出服务请求！");
    }

    /**START======扩展功能：无设计模式，通过接口的方式*/

    @Override
    public void predilectionAnalyze() {
        System.out.printf("现在对企业客户%s进行产品偏好分析...\n", this.getName());
    }

    @Override
    public void worthAnalyze() {
        System.out.printf("现在对企业客户%s进行价值分析...\n", this.getName());
    }
}
