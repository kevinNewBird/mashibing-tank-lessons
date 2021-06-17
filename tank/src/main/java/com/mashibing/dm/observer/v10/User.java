package com.mashibing.dm.observer.v10;

/**
 * description  TODO <BR>
 *
 * @author zhao.song
 * @version 1.0
 * @since 2021/6/11 10:49
 **/
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update() {
        // 1.更新逻辑
        this.setName("tom");
        // 2.事件
        BaseEvent<User> event = new BaseEvent<>(this);
        MyListener.publishEvent(event);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
