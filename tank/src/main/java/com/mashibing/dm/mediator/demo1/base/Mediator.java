package com.mashibing.dm.mediator.demo1.base;

/**
 * 中介对象：用于协调一系列对象的交互
 */
public interface Mediator {

    /**
     * 用于协调一系列对象的交互的方法（相当于组件变化后通知到中介对象，中介对象负责和下一对象通信）
     * <br/>
     * 说明：Mediator相当于电脑中主板的角色，Colleague相当于电脑中的各种组件。
     * 比如看电影的指令发出后，cd驱动器读取数据，读取完成后通知Mediator，由Mediator去告知cpu，cpu解析数据...
     * @param colleague
     */
    void changed(Colleague colleague);
}
