package com.mashibing.dm.command.demo4.pattern.impl;

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

    /**
     * 用来记录组合本菜单的多道菜品，也就是多个命令对象
     */
    private Collection<Command> cols = new ArrayList<>();

    /**
     * 点菜，把菜品加入到菜单中
     *
     * @param command： 客户点的菜
     */
    public void addCommand(Command command) {
        cols.add(command);
    }

    @Override
    public void execute() {
        // 执行菜单其实就是循环执行菜单里面的每个菜
        for (Command cmd : cols) {
            cmd.execute();
        }
    }
}
