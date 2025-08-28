package com.mashibing.dm.command.demo3.pattern.impl;

/**
 * description：厨师对象，做冷菜
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 09:28
 */
public class CoolCook implements CookApi {

    @Override
    public void cook(String name) {
        System.out.printf("凉菜%s已经做好，本厨师正在装盘。\n", name);
    }
}
