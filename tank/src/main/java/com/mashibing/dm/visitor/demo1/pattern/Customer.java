package com.mashibing.dm.visitor.demo1.pattern;

import lombok.Data;

/**
 * 各种客户的父类：抽象的元素对象， 对象接口的顶层接口，定义接受访问的操作
 */
@Data
public abstract class Customer {
    private String id;

    private String name;

    /**
     * 接受访问者对象的访问
     * @param visitor
     */
    public abstract void accept(Visitor visitor);
}
