package com.mashibing.dm.command.demo3.pattern.impl;

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

    /**
     * 命令操作对应的备忘录对象的历史记录。在撤销时使用
     * 数组中有两个元素，第一个是命令执行钱的状态，第二个是命令执行后的状态
     */
    private List<CalcMemento[]> undoMementos = new ArrayList<>();


    /**
     * 命令操作对应的备忘录对象的历史记录。在恢复时使用
     * 数组中有两个元素，第一个是命令执行钱的状态，第二个是命令执行后的状态
     */
    private List<CalcMemento[]> redoMementos = new ArrayList<>();

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
        // 获取对应的备忘录对象，并保存在相应的历史记录中
        CalcMemento m1 = this.addCmd.createMemento();

        // 执行命令
        this.addCmd.execute();
        // 把操作记录到历史记录中
        undoCmds.add(addCmd);

        // 获取执行命令后的备忘录对象
        CalcMemento m2 = this.addCmd.createMemento();
        // 设置到撤销记的历史记录中
        this.undoMementos.add(new CalcMemento[]{m1, m2});
    }

    /**
     * 提供给客户使用，执行减法功能
     */
    public void pressSub() {
        // 获取对应的备忘录对象，并保存在相应的历史记录中
        CalcMemento m1 = this.subCmd.createMemento();

        // 执行命令
        this.subCmd.execute();
        // 把操作记录到历史记录中
        undoCmds.add(subCmd);

        // 获取执行命令后的备忘录对象
        CalcMemento m2 = this.subCmd.createMemento();
        // 设置到撤销记的历史记录中
        this.undoMementos.add(new CalcMemento[]{m1, m2});
    }

    /**
     * 执行撤销操作
     */
    public void pressUndo() {
        if (undoCmds.size() > 0) {
            // 1.取出最后一个命令来撤销
            Command undoCmd = undoCmds.get(undoCmds.size() - 1);
            // 获取对应的备忘录对象
            CalcMemento[] ms = undoMementos.get(undoMementos.size() - 1);

            // 2.执行撤销
            undoCmd.undo(ms[0]);

            // 3.如果有重做命令，把这个命令记录到恢复的历史记录中
            redoCmds.add(undoCmd);
            // 把相应的备忘录对象也添加进去
            redoMementos.add(ms);

            // 4.然后把最后一个命令删除
            undoCmds.remove(undoCmd);
            // 把相应的备忘录对象也删除
            undoMementos.remove(ms);
        } else {
            System.out.println("很抱歉，没有撤销的命令");
        }
    }

    /**
     * 执行重做的操作
     */
    public void pressRedo() {
        if (redoCmds.size() > 0) {
            // 1.取出最后一个命令来恢复
            Command redoCmd = redoCmds.get(redoCmds.size() - 1);
            // 获取对应的备忘录对象
            CalcMemento[] ms = redoMementos.get(redoMementos.size() - 1);

            // 2.执行恢复
            redoCmd.redo(ms[1]);

            // 3.把这个命令记录到可撤销历史记录里
            undoCmds.add(redoCmd);
            // 把相应的备忘录对象也添加进去
            undoMementos.add(ms);

            // 4.然后把最后一个命令删除
            redoCmds.remove(redoCmd);
            // 把相应的备忘录对象也删除
            redoMementos.remove(ms);
        } else {
            System.out.println("很抱歉，没有可恢复的命令");
        }
    }

}
