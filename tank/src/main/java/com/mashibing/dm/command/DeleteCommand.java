package com.mashibing.dm.command;

import java.util.Objects;

/***********************
 * @Description: 拷贝命令 <BR>
 * @author: zhao.song
 * @since: 2021/4/1 9:44
 * @version: 1.0
 ***********************/
public class DeleteCommand extends Command {

    private Content content;
    // 记录消息内容
    private String deleted;

    public DeleteCommand(Content content) {
        Objects.requireNonNull(content.msg, "消息内容不可为空!");
        this.content = content;
    }

    @Override
    public void doit() {
        this.deleted = this.content.msg.substring(0, Math.min(5, this.content.msg.length()));
        this.content.msg = this.content.msg.substring(deleted.length());
    }

    @Override
    public void undo() {
        this.content.msg = this.deleted + this.content.msg;
    }
}
