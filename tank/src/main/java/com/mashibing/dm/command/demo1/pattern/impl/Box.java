package com.mashibing.dm.command.demo1.pattern.impl;

/**
 * description：机箱对象，本身有按钮，持有按钮对应的命令对象。对应Invoker
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 22:26
 */
public class Box {

    /**
     * 开机命令对象
     */
    private Command openCommand;

    /**
     * 设置开机命令对象
     * @param openCommand
     */
    public void setOpenCommand(Command openCommand) {
        this.openCommand = openCommand;
    }

    /**
     * 重启命令对象
     */
    private Command resetCommand;

    public void setResetCommand(Command resetCommand) {
        this.resetCommand = resetCommand;
    }

    /**
     * 提供给客户使用
     * 接受并响应客户请求，相当于按钮被按下触发的方法
     */
    public void pressOpenButton(){
        // 按下按钮执行命令
        openCommand.execute();
    }

    public void pressResetButton(){
        resetCommand.execute();
    }
}
