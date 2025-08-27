package com.mashibing.dm.visitor.demo4.composite;

import com.mashibing.dm.visitor.demo4.base.A;
import com.mashibing.dm.visitor.demo4.vistor.Visitor;

import java.util.Collection;

/**
 * description： 抽象组件对象接口
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:13
 */
public abstract class Component implements A {

    public void addChild(Component c){
        throw new UnsupportedOperationException("不支持的功能！");
    }

    public Collection<Component> getChildren(){
        throw new UnsupportedOperationException("不支持的功能！");
    }

    public abstract void accept(Visitor visitor);
}
