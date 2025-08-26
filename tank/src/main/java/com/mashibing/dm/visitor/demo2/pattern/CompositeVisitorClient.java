package com.mashibing.dm.visitor.demo2.pattern;

import com.mashibing.dm.visitor.demo2.pattern.impl.Composite;
import com.mashibing.dm.visitor.demo2.pattern.impl.Leaf;
import com.mashibing.dm.visitor.demo2.pattern.impl.PrintNameVisitor;

/**
 * description：输出组合模式的每个对象的名字
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 12:07
 */
public class CompositeVisitorClient {

    public static void main(String[] args) {
        // 1.定义所有的组合对象
        Composite root = new Composite("服装");
        Composite c1 = new Composite("男装");
        Composite c2 = new Composite("女装");

        // 2.定义所有的叶子对象
        Leaf leaf1 = new Leaf("衬衣");
        Leaf leaf2 = new Leaf("夹克");
        Leaf leaf3 = new Leaf("裙子");
        Leaf leaf4 = new Leaf("套装");

        // 3.按照树的结构来设置组合对象和叶子对象
        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(leaf1);
        c1.addChild(leaf2);
        c2.addChild(leaf3);
        c2.addChild(leaf4);

        // 4.创建访问者对象
        PrintNameVisitor visitor = new PrintNameVisitor();
        root.accept(visitor);

        /**
         * 节点：服装
         * 节点：男装
         * 叶子：衬衣
         * 叶子：夹克
         * 节点：女装
         * 叶子：裙子
         * 叶子：套装
         */
    }
}
