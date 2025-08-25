package com.mashibing.dm.bridge.demo1.source;

/**
 * 消息的统一接口
 */
public interface Message {

    /**
     * 发送消息
     * @param message： 待发送的消息内容
     * @param toUser： 消息发送的目的人员
     */
    public void send(String message, String toUser);
}
