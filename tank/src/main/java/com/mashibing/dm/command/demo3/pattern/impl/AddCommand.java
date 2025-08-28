package com.mashibing.dm.command.demo3.pattern.impl;

/**
 * description：具体加法的命令实现对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 23:12
 */
public class AddCommand extends AbstractCommand {



    /**
     * 操作的数据，也就是要加上的数据
     */
    private int oprNum;

    public AddCommand(OperationApi operation, int oprNum) {
        super(operation);
        this.oprNum = oprNum;
    }

    @Override
    public void execute() {
        // 转调接收者去真正执行功能
        this.operation.add(this.oprNum);
    }
}
