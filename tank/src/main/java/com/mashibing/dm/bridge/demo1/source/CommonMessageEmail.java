package com.mashibing.dm.bridge.demo1.source;

public class CommonMessageEmail implements Message{

    @Override
    public void send(String message, String toUser) {
        System.out.printf("使用Email的方式，发送消息%s给%s\n", message, toUser);
    }
}
