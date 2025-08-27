package com.mashibing.dm.visitor.demo4.composite;

import java.util.Collection;
import java.util.LinkedList;

/**
 * description：抽象的组合对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/26 17:16
 */
public abstract class Composite extends Component {

    private Collection<Component> childComponents = new LinkedList<>();

    @Override
    public void addChild(Component c) {
        this.childComponents.add(c);
    }

    @Override
    public Collection<Component> getChildren() {
        return this.childComponents;
    }
}
