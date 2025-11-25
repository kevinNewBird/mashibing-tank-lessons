package com.mashibing.dm.command.demo2.pattern.impl;

/**
 * description：具体减法的命令实现对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 23:12
 */
public class SubstractCommand implements Command{

    /**
     * 持有具体执行计算的对象
     */
    private OperationApi operation;

    /**
     * 操作的数据，也就是要减去的数据
     */
    private int oprNum;

    public SubstractCommand(OperationApi operation, int oprNum) {
        this.operation = operation;
        this.oprNum = oprNum;
    }

    @Override
    public void execute() {
        // 转调接收者去真正执行功能
        this.operation.subtract(this.oprNum);
    }

    @Override
    public void undo() {
        // 转调接收者去真正执行功能
        // 命令本身是做减法，那么撤销的时候就是做加法了
        this.operation.add(oprNum);
    }

    @Override
    public void redo() {
        execute();
    }
}
