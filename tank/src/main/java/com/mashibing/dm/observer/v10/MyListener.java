package com.mashibing.dm.observer.v10;

import java.util.LinkedList;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 10:56
 **/
public class MyListener {

    private static LinkedList<Observer> observers = new LinkedList<>();

    public static void addObserver(Observer observer) {
        observers.addLast(observer);
    }

    public static void publishEvent(BaseEvent event) {
        observers.forEach(observer -> observer.onEvent(event));
    }
}
