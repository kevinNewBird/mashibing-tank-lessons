package com.mashibing.dm.visitor.demo3.pattern.impl;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * description：具体的访问者
 * 实现：输出树状结构的对象树
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 14:26
 */
public class PrintStructVisitor implements Visitor {
    // 用来累计对象需要向后退的空格
    private String preStr = "";

    @Override
    public void visitComposite(Composite composite) {
        // 先把自己输出去
        System.out.printf("%s-%s\n", preStr, composite.getName());
        // 如果还包含有子组件，那么就输出这些子组件对象
        List<Component> childComponents = composite.getChildren();
        if (CollectionUtils.isNotEmpty(childComponents)) {
            // 然后添加一个空格
            preStr += " ";
            // 输出当前对象的子对象了
            for (Component c : childComponents) {
                // 递归输出每个子对象
                c.accept(this);
            }
            // 把循环子对象多加入的一个退格给去掉
            preStr = preStr.substring(0, preStr.length() - 1);
        }
    }

    @Override
    public void visitLeaf(Leaf leaf) {
        // 访问到叶子对象的数据
        System.out.printf("%s-%s\n", preStr, leaf.getName());
    }
}
