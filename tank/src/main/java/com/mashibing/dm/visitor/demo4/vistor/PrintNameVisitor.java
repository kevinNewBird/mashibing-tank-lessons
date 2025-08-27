package com.mashibing.dm.visitor.demo4.vistor;

import com.mashibing.dm.visitor.demo4.base.B;
import com.mashibing.dm.visitor.demo4.base.C;
import com.mashibing.dm.visitor.demo4.base.D;
import com.mashibing.dm.visitor.demo4.base.E;

/**
 * description：输出对象名的访问者具体实现
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:32
 */
public class PrintNameVisitor implements Visitor {

    @Override
    public void visitB(B b) {
        System.out.println("节点：" + b.getName());
    }

    @Override
    public void visitC(C c) {
        System.out.println("节点：" + c.getName());
    }

    @Override
    public void visitD(D d) {
        System.out.println("叶子：" + d.getName());
    }

    @Override
    public void visitE(E e) {
        System.out.println("叶子：" + e.getName());
    }
}
