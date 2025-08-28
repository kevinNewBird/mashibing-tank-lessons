package com.mashibing.dm.command.demo4.pattern.impl;

import java.util.concurrent.TimeUnit;

/**
 * description：厨师的接口
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 09:26
 */
public interface CookApi extends Runnable {

    /**
     * 做菜的方法
     *
     * @param name： 菜名
     * @parm tableNum: 点菜桌号
     */
    public void cook(int tableNum, String name);

    @Override
    public default void run() {
        try {
            while (true) {
                Command dish = CommandQueue.poll();
                if (dish == null) {
                    continue;
                }

                // 指定当前厨师实例，设置其为命令对象的接收者
                dish.setCookApi(this);
                // 执行这个命令
                dish.execute();

                // 休息1秒
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
