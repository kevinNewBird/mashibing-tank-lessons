package com.mashibing.dm.visitor.demo2.pattern.impl;

/**
 * description：具体的访问者
 * 实现：输出对象的具体名称，在组合对象的名称前添加“节点：”， 在叶子对象的名称前面添加“叶子：”
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 12:04
 */
public class PrintNameVisitor implements Visitor{
    @Override
    public void visitComposite(Composite composite) {
        System.out.printf("节点：%s\n",composite.getName());
    }

    @Override
    public void visitLeaf(Leaf leaf) {
        System.out.printf("叶子：%s\n",leaf.getName());
    }
}
