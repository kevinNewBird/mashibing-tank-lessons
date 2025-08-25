package com.mashibing.dm.bridge.demo1.pattern;

import com.mashibing.dm.bridge.demo1.pattern.impl.CommonMessage;
import com.mashibing.dm.bridge.demo1.pattern.impl.MessageSMS;

public class BridgeClient {

    public static void main(String[] args) {
        // 创建具体的实现对象
        MessageImplementor impl = new MessageSMS();

        // 创建一个普通消息对象
        CommonMessage m = new CommonMessage(impl);

        // 发送消息
        m.sendMessage("请喝一杯茶", "小李");
    }
}
