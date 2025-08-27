package com.mashibing.dm.visitor.demo4.vistor;

import com.mashibing.dm.visitor.demo4.base.B;
import com.mashibing.dm.visitor.demo4.base.C;
import com.mashibing.dm.visitor.demo4.base.D;
import com.mashibing.dm.visitor.demo4.base.E;

/**
 * description：访问者对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:03
 */
public interface Visitor {

    public void visitB(B b);

    public void visitC(C c);

    public void visitD(D d);

    public void visitE(E e);
}
