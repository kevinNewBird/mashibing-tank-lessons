package com.mashibing.dm.visitor.demo4.decorator;

import com.mashibing.dm.visitor.demo4.base.A;
import com.mashibing.dm.visitor.demo4.composite.Composite;
import com.mashibing.dm.visitor.demo4.vistor.Visitor;

/**
 * description：装饰器修饰的组合节点对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:18
 */
public abstract class CompositeDecorator extends Composite implements A {

    protected final A a;

    public CompositeDecorator(A a) {
        this.a = a;
    }

}
