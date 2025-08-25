package com.mashibing.dm.bridge.demo1.pattern.impl;

import com.mashibing.dm.bridge.demo1.pattern.MessageImplementor;

/**
 * 以站内消息的方式发送消息
 */
public class MessageSMS implements MessageImplementor {
    @Override
    public void send(String message, String toUser) {
        System.out.printf("使用站内短消息的方式，发送消息%s给%s\n", message, toUser);
    }
}
