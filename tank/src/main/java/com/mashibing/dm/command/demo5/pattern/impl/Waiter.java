package com.mashibing.dm.command.demo5.pattern.impl;

/**
 * description：服务员
 * 负责组合菜单，负责组装每个菜和具体的实现者
 * 还负责执行调用，相当于标准Command模式的Client + Invoker
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 09:54
 */
public class Waiter {

    /**
     * 持有一个宏命令对象 -- 菜单
     */
    private final MenuCommand menuCommand = new MenuCommand();

    /**
     * 客户点菜
     *
     * @param cmd：客户点的菜，每道菜是一个命令对象
     */
    public void orderDish(Command cmd) {
        // 1.添加到菜单中
        menuCommand.addCommand(cmd);
    }

    /**
     * 客户点菜完毕，执行宏命令
     */
    public void orderOver() {
        this.menuCommand.execute();
    }
}
