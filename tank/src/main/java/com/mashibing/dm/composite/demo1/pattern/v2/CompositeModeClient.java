package com.mashibing.dm.composite.demo1.pattern.v2;

import com.mashibing.dm.composite.demo1.pattern.v2.impl.Composite;
import com.mashibing.dm.composite.demo1.pattern.v2.impl.Leaf;

/**
 * description：组合模式的实现测试
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 10:37
 */
public class CompositeModeClient {

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

        // 4.调用根对象的输出功能来输出整棵树
        root.printStruct("");

        /**
         * -服装
         *  -男装
         *   -衬衣
         *   -夹克
         *  -女装
         *   -裙子
         *   -套装
         */
        System.out.println("------------------------------------->");
        // 5.删除一个组合节点
        root.removeChild(c1);
        root.printStruct("");
    }
}
