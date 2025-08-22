package com.mashibing.dm.visitor.demo1.source;

import lombok.Data;
/**
 * 各种客户的父类
 */
@Data
public abstract class Customer {

    private String id;

    private String name;

    public abstract void serviceRequest();


    /**START======扩展功能：无设计模式，通过接口的方式*/

    /**
     * 客户对公司产品的偏好分析
     */
    public abstract void predilectionAnalyze();

    /**
     * 客户价值分析
     */
    public abstract void worthAnalyze();
}
