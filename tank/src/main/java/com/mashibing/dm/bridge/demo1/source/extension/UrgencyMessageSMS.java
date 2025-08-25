package com.mashibing.dm.bridge.demo1.source.extension;

public class UrgencyMessageSMS implements UrgencyMessage {

    @Override
    public Object watch(String messageId) {
        // 获取相应的数据，组织成监控的数据对象，然后返回
        return null;
    }

    @Override
    public void send(String message, String toUser) {
        message = "加急：" + message;
        System.out.printf("使用站内短消息的方式，发送消息%s给%s\n", message, toUser);
    }
}
