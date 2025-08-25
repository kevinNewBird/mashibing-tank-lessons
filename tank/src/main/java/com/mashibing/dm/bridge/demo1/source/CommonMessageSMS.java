package com.mashibing.dm.bridge.demo1.source;

/**
 * 以站内短消息的方式发送普通消息
 */
public class CommonMessageSMS implements Message {
    /**
     * 发送站内消息
     * @param message： 待发送的消息内容
     * @param toUser： 消息发送的目的人员
     */
    @Override
    public void send(String message, String toUser) {
        System.out.printf("使用站内短消息的方式，发送消息%s给%s\n", message, toUser);
    }
}
