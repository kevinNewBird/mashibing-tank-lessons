package com.mashibing.dm.bridge.demo1.pattern.impl;

import com.mashibing.dm.bridge.demo1.pattern.AbstractMessage;
import com.mashibing.dm.bridge.demo1.pattern.MessageImplementor;

/**
 * 普通消息的实现
 */
public class CommonMessage extends AbstractMessage {

    /**
     * 构造方法，传入实现部分的对象
     *
     * @param impl ：实现部分的对象
     */
    public CommonMessage(MessageImplementor impl) {
        super(impl);
    }


    /**
     * 对于普通消息，直接掉调用父类的方法
     * @param message：要发送的消息内容
     * @param toUser：消息发送的目的人员
     */
    @Override
    public void sendMessage(String message, String toUser) {
        super.sendMessage(message, toUser);
    }
}
