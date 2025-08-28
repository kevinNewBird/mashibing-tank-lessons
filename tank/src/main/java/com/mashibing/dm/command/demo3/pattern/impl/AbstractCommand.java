package com.mashibing.dm.command.demo3.pattern.impl;

/**
 * description：抽象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 23:14
 */
public abstract class AbstractCommand implements Command {

    /**
     * 持有具体执行计算的对象
     */
    protected OperationApi operation;

    public AbstractCommand(OperationApi operation) {
        this.operation = operation;
    }

    @Override
    public CalcMemento createMemento() {
        return this.operation.createMemento();
    }

    @Override
    public void redo(CalcMemento memento) {
        this.operation.setMemento(memento);
    }

    @Override
    public void undo(CalcMemento memento) {
        this.operation.setMemento(memento);
    }
}
