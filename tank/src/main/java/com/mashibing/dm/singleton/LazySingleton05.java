package com.mashibing.dm.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

/***********************
 * Description: 懒汉式<BR>
 *      针对线程不安全的问题,内部静态类
 * author: zhao.song
 * date: 2021/1/25 14:21
 * version: 1.0
 ***********************/
public class LazySingleton05 {

    private static Logger logger = LoggerFactory.getLogger(LazySingleton05.class);


    private LazySingleton05(){}

    public static LazySingleton05 getInstance() {
        return LazySingleton05.LazySingletonHolder.INSTANCE;
    }

    private static class LazySingletonHolder{
        public static LazySingleton05 INSTANCE = new LazySingleton05();
    }

    public static void main(String[] args) {
        IntStream.range(0,100).forEach(num->{
            new Thread(()->{
                System.out.println(LazySingleton05.getInstance().hashCode());
            }).start();
        });
    }
}
