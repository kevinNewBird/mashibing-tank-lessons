package com.mashibing.dm.visitor.demo4.decorator;

import com.mashibing.dm.visitor.demo4.composite.Component;
import com.mashibing.dm.visitor.demo4.vistor.Visitor;
import com.mashibing.dm.visitor.demo4.base.A;

/**
 * description：装饰器修饰的叶子节点对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:00
 */
public abstract class Decorator extends Component implements A{

    protected final A a;


    public Decorator(A a) {
        this.a = a;
    }
}
