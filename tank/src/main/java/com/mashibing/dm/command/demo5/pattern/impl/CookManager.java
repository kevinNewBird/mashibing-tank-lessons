package com.mashibing.dm.command.demo5.pattern.impl;

/**
 * description：用来控制诗序需要创建厨师
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 15:20
 */
public class CookManager {

    /**
     * 用来控制诗序需要创建厨师
     */
    private volatile static boolean runFlag = false;

    /**
     * 运行厨师管理，创建厨师对象并启动他们相应的线程
     */
    public static void runCookManager() {
        if (!runFlag) {
            runFlag = true;
            // 创建三位厨师
            CoolCook cook1 = new CoolCook("张三");
            CoolCook cook2 = new CoolCook("李四");
            HotCook cook3 = new HotCook("王五");

            // 启动线程
            new Thread(cook1).start();
            new Thread(cook2).start();
            new Thread(cook3).start();
        }
    }

}
