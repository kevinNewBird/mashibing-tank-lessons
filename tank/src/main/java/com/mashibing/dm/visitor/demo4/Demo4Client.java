package com.mashibing.dm.visitor.demo4;

import com.mashibing.dm.visitor.demo4.base.B;
import com.mashibing.dm.visitor.demo4.base.C;
import com.mashibing.dm.visitor.demo4.base.D;
import com.mashibing.dm.visitor.demo4.base.E;
import com.mashibing.dm.visitor.demo4.decorator.BCompositeDecorator;
import com.mashibing.dm.visitor.demo4.decorator.CCompositeDecorator;
import com.mashibing.dm.visitor.demo4.decorator.DDecorator;
import com.mashibing.dm.visitor.demo4.decorator.EDecorator;
import com.mashibing.dm.visitor.demo4.vistor.PrintNameVisitor;

/**
 * description：客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:36
 */
public class Demo4Client {

    public static void main(String[] args) {
        C c = new C("c", new D("d"));
        E e = new E("e");
        B b = new B("b", c, e);

        BCompositeDecorator bCompositeDecorator = new BCompositeDecorator(b);
        CCompositeDecorator cCompositeDecorator = new CCompositeDecorator(c);
        DDecorator dDecorator = new DDecorator(c.getD());
        EDecorator eDecorator = new EDecorator(b.getE());

        bCompositeDecorator.addChild(cCompositeDecorator);
        bCompositeDecorator.addChild(eDecorator);
        cCompositeDecorator.addChild(dDecorator);

        PrintNameVisitor visitor = new PrintNameVisitor();
        bCompositeDecorator.accept(visitor);

        /**
         * 节点：b
         * 节点：c
         * 叶子：d
         * 叶子：e
         */
    }
}
