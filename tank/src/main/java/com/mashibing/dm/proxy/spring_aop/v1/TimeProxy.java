package com.mashibing.dm.proxy.spring_aop.v1;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/20 21:26
 * @version: 1.0
 ***********************/
public class TimeProxy {

    public void before() {
        System.out.println("before....");
    }

    public void after() {
        System.out.println("after....");
    }
}
