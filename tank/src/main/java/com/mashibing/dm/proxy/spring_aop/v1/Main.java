package com.mashibing.dm.proxy.spring_aop.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/20 21:24
 * @version: 1.0
 ***********************/
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        Tank tank = context.getBean("tank", Tank.class);
        tank.move();
    }
}
