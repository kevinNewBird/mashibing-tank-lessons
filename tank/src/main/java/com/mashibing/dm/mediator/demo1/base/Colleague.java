package com.mashibing.dm.mediator.demo1.base;

/**
 * 被协调的一系列对象的接口
 */
public abstract class Colleague {

    private final Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }
}
