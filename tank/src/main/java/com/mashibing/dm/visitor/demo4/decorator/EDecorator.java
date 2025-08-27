package com.mashibing.dm.visitor.demo4.decorator;

import com.mashibing.dm.visitor.demo4.base.A;
import com.mashibing.dm.visitor.demo4.base.D;
import com.mashibing.dm.visitor.demo4.base.E;
import com.mashibing.dm.visitor.demo4.vistor.Visitor;

/**
 * description：C装饰器叶子对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:29
 */
public class EDecorator extends Decorator {

    public EDecorator(A a) {
        super(a);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitE((E) a);
    }
}
