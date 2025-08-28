package com.mashibing.dm.command.demo1.pattern.impl;

/**
 * description：开机命令的实现
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 22:21
 */
public class OpenCommand implements Command {

    /**
     * 持有真正实现命令的接收者--主板对象
     */
    private GigaMainBoard mainBoard;

    public OpenCommand(GigaMainBoard mainBoard) {
        this.mainBoard = mainBoard;
    }

    /**
     *  提供给客户使用
     */
    @Override
    public void execute() {
        // 对于命令对象，根本不知道如何开机，会转调主板对象
        // 让主板去完成开机的功能
        mainBoard.open();
    }
}
