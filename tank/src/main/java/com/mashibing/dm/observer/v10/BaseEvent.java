package com.mashibing.dm.observer.v10;

/**
 * description  事件对象 <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 10:45
 **/
public class BaseEvent<T> {

    T source;

    public BaseEvent(T source) {
        this.source = source;
    }

    public T getSource() {
        return this.source;
    }


}
