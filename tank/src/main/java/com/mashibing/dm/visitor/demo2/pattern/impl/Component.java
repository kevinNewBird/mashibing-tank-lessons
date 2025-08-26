package com.mashibing.dm.visitor.demo2.pattern.impl;

import java.util.List;

/**
 * description：抽象的组件对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 10:11
 */
public abstract class Component {

    /**
     * 记录父组件对象
     */
    protected Component parent;


    /**
     * 获取一个组件的父组件对象
     * @return ： 一个组件的父组件对象
     */
    public Component getParent() {
        return parent;
    }

    /**
     * 设置一个组件的父组件对象
     * @param parent：一个组件的父组件对象
     */
    public void setParent(Component parent) {
        this.parent = parent;
    }

    /**
     * 返回某个组件的子组件对象
     * @return 某个组件的子组件对象
     */
    public List<Component> getChildren(){
        throw new UnsupportedOperationException(Component.class.getName() + "不支持这个功能！");
    }

    /**
     * 输出组件自身的名称
     * @param preStr： 前缀
     */
    public abstract void printStruct(String preStr);

    /**
     * 向组合对象中加入组件对象
     * @param child：被加入组合对象中的组件对象
     */
    public void addChild(Component child){
        throw new UnsupportedOperationException(Component.class.getName() + "不支持这个功能！");
    }

    /**
     * 从组合对象中移出某个组件对象
     * @param child：被移出的组件对象
     */
    public void removeChild(Component child) {
        throw new UnsupportedOperationException(Component.class.getName() + "不支持这个功能！");
    }

    /**
     * 返回某个索引对应的组件对象
     * @param index：需要获取的组件对象的索引，索引从0开始
     * @return： 索引对应的组件对象
     */
    public Component getChildren(int index) {
        throw new UnsupportedOperationException(Component.class.getName() + "不支持这个功能！");
    }

    public abstract String getName();

    public abstract void accept(Visitor visitor);
}
