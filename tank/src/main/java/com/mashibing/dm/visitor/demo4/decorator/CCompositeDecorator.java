package com.mashibing.dm.visitor.demo4.decorator;

import com.mashibing.dm.visitor.demo4.base.A;
import com.mashibing.dm.visitor.demo4.base.B;
import com.mashibing.dm.visitor.demo4.base.C;
import com.mashibing.dm.visitor.demo4.composite.Component;
import com.mashibing.dm.visitor.demo4.vistor.Visitor;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * description：B的装饰器组合对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:24
 */
public class CCompositeDecorator extends CompositeDecorator{

    public CCompositeDecorator(A a) {
        super(a);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitC((C) a);
        Collection<Component> childComponents = getChildren();
        if (CollectionUtils.isNotEmpty(childComponents)) {
            for (Component c : childComponents) {
                c.accept(visitor);
            }
        }
    }
}
