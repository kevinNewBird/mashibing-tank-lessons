package com.mashibing.dm.command.demo1.pattern.impl;

/**
 * description：重启命令的实现
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 22:45
 */
public class ResetCommand implements Command {

    /**
     * 持有真正实现命令的接收者--主板对象
     */
    private GigaMainBoard mainBoard;

    public ResetCommand(GigaMainBoard mainBoard) {
        this.mainBoard = mainBoard;
    }

    @Override
    public void execute() {
        mainBoard.reset();
    }
}
