package com.mashibing.dm.memento.demo1.pattern.v1.impl;


/**
 * description：负责保存模拟运行流程A的对象的备忘录对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 22:29
 */
public class FlowAMementoCareTaker {

    /**
     * 记录被保存的备忘录对象
     */
    private FlowAMockMemento memento;

    /**
     * 保存备忘录对象
     *
     * @param memento： 被保存备忘录对象
     */
    public void saveMemento(com.mashibing.dm.memento.demo1.pattern.v1.impl.FlowAMockMemento memento) {
        this.memento = memento;
    }

    /**
     * 获取被保存的备忘录对象
     *
     * @return FlowAMockMemento:被保存的备忘录对象
     */
    public com.mashibing.dm.memento.demo1.pattern.v1.impl.FlowAMockMemento retrieveMemento() {
        return this.memento;
    }

}
