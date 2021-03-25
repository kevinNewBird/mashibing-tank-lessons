package com.mashibing.dm.proxy.spring_aop.v2;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/20 21:26
 * @version: 1.0
 ***********************/
@Aspect
public class TimeProxy {

    @Before("execution(void com.mashibing.dm.proxy.spring_aop.v2.Tank.*())")
    public void before() {
        System.out.println("before2....");
    }
    @After("execution(void com.mashibing.dm.proxy.spring_aop.v2.Tank.*())")
    public void after() {
        System.out.println("after2....");
    }
}
