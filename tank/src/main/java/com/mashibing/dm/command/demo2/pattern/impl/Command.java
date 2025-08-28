package com.mashibing.dm.command.demo2.pattern.impl;

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
    void undo();

    /**
     * 恢复操作
     */
    void redo();
}
