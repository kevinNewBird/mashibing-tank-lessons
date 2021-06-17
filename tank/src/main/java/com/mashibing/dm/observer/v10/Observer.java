package com.mashibing.dm.observer.v10;

import java.awt.*;

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
