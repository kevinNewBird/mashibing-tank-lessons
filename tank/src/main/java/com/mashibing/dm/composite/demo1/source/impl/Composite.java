package com.mashibing.dm.composite.demo1.source.impl;

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
public class Composite {

    /**
     * 用来记录包含的其他组合对象
     */
    private Collection<Composite> childComposite = new ArrayList<>();

    /**
     * 用来记录包含的其他叶子节点
     */
    private Collection<Leaf> childLeaf = new ArrayList<>();

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
     * 向组合对象加入被它包含的其他组合对象
     *
     * @param c：被它包含的其他组合对象
     */
    public void addComposite(Composite c) {
        this.childComposite.add(c);
    }

    /**
     * 向组合对象加入被它包含的叶子对象
     *
     * @param leaf： 被它包含的叶子对象
     */
    public void addLeaf(Leaf leaf) {
        this.childLeaf.add(leaf);
    }

    /**
     * 输出组合对象自身的结构
     *
     * @param preStr: 前缀，主要是按照层级拼接的空格，实现向后缩进
     */
    public void printStruct(String preStr) {
        // 1.先把自己输出去
        System.out.printf("%s-%s\n", preStr, this.name);

        // 2.然后添加一个空格，表示向后缩进一个空格，输出自己包含的叶子对象
        preStr += " ";
        for (Leaf leaf : childLeaf) {
            leaf.printStruct(preStr);
        }

        // 3.输出当前对象的子对象
        for (Composite c : childComposite) {
            c.printStruct(preStr);
        }

    }

}
