package com.mashibing.dm.visitor.demo2.pattern.impl;

/**
 * description：访问者接口对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 11:57
 */
public interface Visitor {

    /**
     * 访问组合对象， 相当于给组合对象添加访问者的功能
     *
     * @param composite： 组合对象
     */
    public void visitComposite(Composite composite);

    /**
     * 访问叶子对象， 相当于给叶子对象添加访问者的功能
     *
     * @param leaf： 叶子对象
     */
    public void visitLeaf(Leaf leaf);
}
