package com.mashibing.dm.visitor.demo3.pattern.impl;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description：组合对象，可以包含其他组合对象或者叶子对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 09:28
 */
public class Composite extends Component {

    /**
     * 用来记录包含的其他组合对象或叶子对象
     */
    private List<Component> childComponents;

    /**
     * 组合对象的名字
     */
    private String name;

    /**
     * 构造方法，传入组合对象的名字
     *
     * @param name：组合对象的名字
     */
    public Composite(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 向组合对象加入被它包含的其他组合对象或叶子对象
     *
     * @param c：被它包含的其他组合对象
     */
    public void addChild(Component c) {
        if (childComponents == null) {
            this.childComponents = new ArrayList<>();
        }
        this.childComponents.add(c);

        // 设置组件的父组件对象
        c.setParent(this);
    }

    @Override
    public void removeChild(Component child) {
        if (CollectionUtils.isEmpty(this.childComponents)) {
            return;
        }

        // 查找到要删除组件在集合中的索引位置
        int idx = this.childComponents.indexOf(child);

        if (idx != -1) { // 说明child对象为当前Composite下的子分类
            // 先把被删除的商品类别对象的父商品类别，设置成为被删除商品的类别的子类别的父商品类别
            for (Component c : child.getChildren()) {
                // 删除的组件对象是本实例的一个子组件对象
                c.setParent(this);
                // 把被删除的商品类型对象的子组件对象添加到当前实例中
                this.childComponents.add(c);
            }
            // 删除child
            this.childComponents.remove(child);
        }
    }

    @Override
    public List<Component> getChildren() {
        return this.childComponents;
    }

    /**
     * 输出组合对象自身的结构
     *
     * @param preStr: 前缀，主要是按照层级拼接的空格，实现向后缩进
     */
    public void printStruct(String preStr) {
        // 1.先把自己输出去
        System.out.printf("%s-%s\n", preStr, this.name);

        // 2.然后输出自己包含的组合对象或叶子对象
        if (CollectionUtils.isEmpty(this.childComponents)) {
            return;
        }
        // 添加一个空格，表示向后缩进一个空格
        preStr += " ";
        // 输出当前对象的子对象
        for (Component c : this.childComponents) {
            // 递归输出每个子对象
            c.printStruct(preStr);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        // 回调访问者对象的相应方法
        visitor.visitComposite(this);

        // 需要注释下面的迭代代码，交由访问者完成对象树的拼装
        // 循环子元素，让子元素也接受访问
//        for (Component c : this.childComponents) {
//            // 调用子对象接受访问，变相实现递归
//            c.accept(visitor);
//        }
    }
}
