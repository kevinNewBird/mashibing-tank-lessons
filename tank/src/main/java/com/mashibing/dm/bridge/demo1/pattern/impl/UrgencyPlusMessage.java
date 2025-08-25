package com.mashibing.dm.bridge.demo1.pattern.impl;

import com.mashibing.dm.bridge.demo1.pattern.AbstractMessage;
import com.mashibing.dm.bridge.demo1.pattern.MessageImplementor;

/**
 * 特急信息扩展
 */
public class UrgencyPlusMessage extends AbstractMessage {
    /**
     * 构造方法，传入实现部分的对象
     *
     * @param impl ：实现部分的对象
     */
    public UrgencyPlusMessage(MessageImplementor impl) {
        super(impl);
    }


    @Override
    public void sendMessage(String message, String toUser) {
        message = "特急：" + message;
        super.sendMessage(message, toUser);
        // 还需要增加一条待催促的信息
    }
}
