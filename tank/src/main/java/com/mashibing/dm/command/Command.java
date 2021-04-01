package com.mashibing.dm.command;

/***********************
 * @Description: 命令抽象类 <BR>
 * @author: zhao.song
 * @since: 2021/4/1 0:49
 * @version: 1.0
 ***********************/
public abstract class Command {

    public abstract void doit(); // exec run

    public abstract void undo(); // cancel

}
