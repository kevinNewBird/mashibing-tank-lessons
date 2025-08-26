package com.mashibing.dm.composite.demo1.source;

import com.mashibing.dm.composite.demo1.source.impl.Composite;
import com.mashibing.dm.composite.demo1.source.impl.Leaf;

/**
 * description：服装测试客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 09:40
 */
public class ClothingClient {

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
        root.addComposite(c1);
        root.addComposite(c2);
        c1.addLeaf(leaf1);
        c1.addLeaf(leaf2);
        c2.addLeaf(leaf3);
        c2.addLeaf(leaf4);

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
    }
}
