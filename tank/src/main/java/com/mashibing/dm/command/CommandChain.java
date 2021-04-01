package com.mashibing.dm.command;

import java.util.LinkedList;

/***********************
 * @Description: 责任链 + 命令模式<BR>
 * @author: zhao.song
 * @since: 2021/4/1 23:37
 * @version: 1.0
 ***********************/
public class CommandChain {

    LinkedList<Command> cmdContainer = new LinkedList<>();

    int step = 0;

    public CommandChain add(Command c){
        cmdContainer.add(c);
        return this;
    }

    public void execCommand() {
        if (cmdContainer.size()==step) {
            return;
        }
        cmdContainer.get(step++).execCommand(this);
    }
}
