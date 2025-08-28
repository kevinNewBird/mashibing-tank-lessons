package com.mashibing.dm.command.demo5.pattern.impl;

import java.util.ArrayList;
import java.util.Collection;

/**
 * description：菜单对象，是个宏命令对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 09:38
 */
public class MenuCommand implements Command {

    @Override
    public void setCookApi(CookApi cookApi) {
        // 什么都不用做
    }

    @Override
    public int getTableNum() {
        // 什么都不用做
        return 0;
    }

    /**
     * 用来记录组合本菜单的多道菜品，也就是多个命令对象
     */
    private Collection<Command> cols = new ArrayList<>();

    public Collection<Command> getCommands(){
        return this.cols;
    }

    /**
     * 点菜，把菜品加入到菜单中
     *
     * @param command： 客户点的菜
     */
    public void addCommand(Command command) {
        cols.add(command);
    }

    /**
     * 执行菜单
     */
    @Override
    public void execute() {
        // 执行菜单就是把菜单传递给后厨
        CommandQueue.addMenu(this);
    }
}
