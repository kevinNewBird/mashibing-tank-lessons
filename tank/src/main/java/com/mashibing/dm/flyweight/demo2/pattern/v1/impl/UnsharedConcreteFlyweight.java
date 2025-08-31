package com.mashibing.dm.flyweight.demo2.pattern.v1.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * description：不需要共享的享元对象的实现，也是组合模式中的组合对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/31 09:15
 */
public class UnsharedConcreteFlyweight implements FlyWeight{

    /**
     * 记录每个组合对象所包含的子组件
     */
    private final List<FlyWeight> COMPOSITE_AUTHS = new ArrayList<>();

    @Override
    public boolean match(String securityEntity, String permit) {
        for (FlyWeight f : COMPOSITE_AUTHS) {
            if (f.match(securityEntity, permit)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(FlyWeight f) {
        COMPOSITE_AUTHS.add(f);
    }
}
