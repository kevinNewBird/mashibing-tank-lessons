package com.mashibing.dm.mediator;

/**
 * 中介者接口，作为中转对象，每个实际交互者都会持有该对象
 */
public interface Mediator {

    /**
     * 同事对象在自身改变的时候来通知中介者的方法
     * ，让中介者去负责相应的与其他同事对象的交互
     *
     * @param colleague
     */
    void changed(Colleague colleague);
}
