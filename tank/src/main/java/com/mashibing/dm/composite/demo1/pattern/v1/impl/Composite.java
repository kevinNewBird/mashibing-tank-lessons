package com.mashibing.dm.composite.demo1.pattern.v1.impl;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

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
    private Collection<Component> childComponents = new ArrayList<>();

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

    /**
     * 向组合对象加入被它包含的其他组合对象或叶子对象
     *
     * @param c：被它包含的其他组合对象
     */
    public void addChild(Component c) {
        this.childComponents.add(c);
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

}
