package com.mashibing.dm.command.demo5.pattern.impl;


import java.util.LinkedList;
import java.util.Queue;

/**
 * description：命令队列类
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 14:54
 */
public class CommandQueue {

    /**
     * 用来存储命令对象的队列
     */
    private static Queue<Command> QUEUE = new LinkedList<>();

    public static void addMenu(MenuCommand menu) {
        synchronized (QUEUE) {
            QUEUE.addAll(menu.getCommands());
        }
    }

    public static Command poll() {
        synchronized (QUEUE) {
            // 返回队列里的一个元素（先到先处理的远程），队列为空返回null
            return QUEUE.poll();
        }
    }
}
