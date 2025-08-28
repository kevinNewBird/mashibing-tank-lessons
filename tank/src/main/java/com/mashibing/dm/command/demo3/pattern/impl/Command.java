package com.mashibing.dm.command.demo3.pattern.impl;

/**
 * description：命令接口，声明执行的操作，支持可撤销操作
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 23:10
 */
public interface Command {

    /**
     * 执行命令对应的操作
     */
    void execute();

    /**
     * 执行撤销命令对应的操作
     */
    void undo(CalcMemento memento);

    void redo(CalcMemento memento);

    /**
     * 创建保存原发器对象状态的备忘录对象
     * @return
     */
    public CalcMemento createMemento();
}
