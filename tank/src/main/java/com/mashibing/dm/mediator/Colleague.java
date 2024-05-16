package com.mashibing.dm.mediator;

/**
 * 同事对象的父类，用于约束对象之间需要通过中介者进行交互的对象
 */
public abstract class Colleague {


    /**
     * 中介者对象
     */
    private Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return this.mediator;
    }
}
