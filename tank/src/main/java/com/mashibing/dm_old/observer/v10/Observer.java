package com.mashibing.dm_old.observer.v10;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 10:47
 **/
public interface Observer<E extends BaseEvent> {

    void onEvent(E event);
}
