package com.mashibing.dm.bridge.demo1.pattern.impl;

import com.mashibing.dm.bridge.demo1.pattern.MessageImplementor;

/**
 * 扩展发送消息的方式
 */
public class MessageMobile implements MessageImplementor {
    @Override
    public void send(String message, String toUser) {
        System.out.printf("使用手机短消息的方式，发送消息%s给%s\n", message, toUser);
    }
}
