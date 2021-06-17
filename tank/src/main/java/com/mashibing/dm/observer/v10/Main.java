package com.mashibing.dm.observer.v10;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 11:11
 **/
public class Main {

    public static void main(String[] args) {
//        MyListener.addObserver(new UserObserver());
        Observer<BaseEvent<User>> observer = new UserObserver();
        User commonUser = new User("kevin");
        commonUser.update();

    }
}
