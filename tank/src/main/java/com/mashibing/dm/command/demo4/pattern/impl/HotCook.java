package com.mashibing.dm.command.demo4.pattern.impl;

import java.util.concurrent.TimeUnit;

/**
 * description：厨师对象，做热菜
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 09:27
 */
public class HotCook implements CookApi {

    /**
     * 厨师名字
     */
    private String name;

    public HotCook(String name) {
        this.name = name;
    }

    @Override
    public void cook(int tableNum, String name) {
        try {
            // 做菜时间取随机数
            int cookTime = (int) (30 * Math.random());

            System.out.printf("%s厨师【金牌】正在为%s号桌做：%s\n", this.name, tableNum, name);
            TimeUnit.MILLISECONDS.sleep(cookTime);
            System.out.printf("%s厨师【金牌】为%s号桌做好了：%s，共计耗时%s秒\n", this.name, tableNum, name, cookTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
