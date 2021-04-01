package com.mashibing.dm.command;

import java.util.Objects;

/***********************
 * @Description: 拷贝命令 <BR>
 * @author: zhao.song
 * @since: 2021/4/1 9:44
 * @version: 1.0
 ***********************/
public class CopyCommand extends Command {

    private Content content;
    // 记录消息内容
    private String insertConMsg;

    public CopyCommand(Content content) {
        Objects.requireNonNull(content.msg, "消息内容不可为空!");
        this.content = content;
    }

    @Override
    public void doit() {
        this.insertConMsg = this.content.msg;
        this.content.msg += insertConMsg;
    }

    @Override
    public void undo() {
        this.content.msg = this.content.msg.substring(0, this.content.msg.length() - insertConMsg.length());
    }

    @Override
    public void execCommand(CommandChain chain) {
        doit();
        chain.execCommand();
        undo();
    }
}
