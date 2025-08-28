package com.mashibing.dm.command.demo2.pattern.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * description：计算器类，Invoker
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 23:16
 */
public class Calculator {

    /**
     * 持有加法的命令对象
     */
    private Command addCmd;

    /**
     * 持有减法的命令对象
     */
    private Command subCmd;

    /**
     * 记录撤销命令
     */
    private List<Command> undoCmds = new ArrayList<>();

    /**
     * 记录重做命令（有撤销才有重做）
     */
    private List<Command> redoCmds = new ArrayList<>();

    public void setAddCmd(Command addCmd) {
        this.addCmd = addCmd;
    }

    public void setSubCmd(Command subCmd) {
        this.subCmd = subCmd;
    }

    /**
     * 提供给客户使用，执行加法功能
     */
    public void pressAdd() {
        this.addCmd.execute();
        undoCmds.add(addCmd);
    }

    /**
     * 提供给客户使用，执行减法功能
     */
    public void pressSub() {
        this.subCmd.execute();
        undoCmds.add(subCmd);
    }

    /**
     * 执行撤销操作
     */
    public void pressUndo() {
        if (undoCmds.size() > 0) {
            // 取出最后一个
            Command undoCmd = undoCmds.get(undoCmds.size() - 1);
            undoCmd.undo();
            // 如果有重做命令
            redoCmds.add(undoCmd);
            undoCmds.remove(undoCmd);
        } else {
            System.out.println("很抱歉，没有撤销的命令");
        }
    }

    /**
     * 执行重做的操作
     */
    public void pressRedo() {
        if (redoCmds.size() > 0) {
            Command redoCmd = redoCmds.get(redoCmds.size() - 1);
            redoCmd.redo();

            // 把这个命令记录到可撤销历史记录里
            undoCmds.add(redoCmd);

            redoCmds.remove(redoCmd);
        } else {
            System.out.println("很抱歉，没有可恢复的命令");
        }
    }

}
