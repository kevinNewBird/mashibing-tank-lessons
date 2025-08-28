package com.mashibing.dm.command.demo3.pattern.impl;

/**
 * description：厨师对象，做热菜
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/28 09:27
 */
public class HotCook implements CookApi {

    @Override
    public void cook(String name) {
        System.out.println("本厨师正在做：" + name);
    }
}
