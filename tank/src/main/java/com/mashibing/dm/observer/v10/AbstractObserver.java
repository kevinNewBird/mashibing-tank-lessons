package com.mashibing.dm.observer.v10;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 11:01
 **/
public abstract class AbstractObserver<E extends BaseEvent> implements Observer<E>{

    public AbstractObserver() {
        MyListener.addObserver(this);
    }

}
