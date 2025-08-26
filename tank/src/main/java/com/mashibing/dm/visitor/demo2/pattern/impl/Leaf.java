package com.mashibing.dm.visitor.demo2.pattern.impl;

/**
 * description：叶子节点对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 09:24
 */
public class Leaf extends Component {

    /**
     * 叶子对象的名字
     */
    private String name;


    /**
     * 构造方法，传入叶子对象的名字
     *
     * @param name： 叶子对象的名字
     */
    public Leaf(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 输出叶子对象的结构，叶子对象没有子对象，也就是输出叶子对象的名字
     *
     * @param preStr： 前缀，主要是按照层级拼接的空格，实现向后缩进
     */
    public void printStruct(String preStr) {
        System.out.printf("%s-%s\n", preStr, this.name);
    }

    @Override
    public void accept(Visitor visitor) {
        // 回调访问者对象的相应方法
        visitor.visitLeaf(this);
    }
}
