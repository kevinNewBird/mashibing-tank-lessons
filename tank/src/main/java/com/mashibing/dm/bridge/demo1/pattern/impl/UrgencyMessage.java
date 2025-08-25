package com.mashibing.dm.bridge.demo1.pattern.impl;

import com.mashibing.dm.bridge.demo1.pattern.AbstractMessage;
import com.mashibing.dm.bridge.demo1.pattern.MessageImplementor;

/**
 * 加急消息的实现
 */
public class UrgencyMessage extends AbstractMessage {
    /**
     * 构造方法
     *
     * @param impl ：实现部分的对象
     */
    public UrgencyMessage(MessageImplementor impl) {
        super(impl);
    }

    @Override
    public void sendMessage(String message, String toUser) {
        message = "加急：" + message;
        super.sendMessage(message, toUser);
    }

    /**
     * 扩展自己的新功能，监控某消息的处理过程
     * @param messageId
     * @return
     */
    public Object watch(String messageId){
        // 获取相应的数据，组织成监控的数据对象，然后返回
        return null;
    }
}
