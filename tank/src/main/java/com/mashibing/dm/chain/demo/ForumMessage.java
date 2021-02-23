package com.mashibing.dm.chain.demo;

/***********************
 * @Description: 模拟论坛消息 <BR>
 * @author: zhao.song
 * @since: 2021/2/20 0:22
 * @version: 1.0
 ***********************/
public class ForumMessage {

    private String name;
    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
