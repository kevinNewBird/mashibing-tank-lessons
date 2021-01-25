package com.mashibing.dm.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

/***********************
 * Description: 懒汉式<BR>
 *      针对线程不安全的问题,枚举
 * author: zhao.song
 * date: 2021/1/25 14:21
 * version: 1.0
 ***********************/
public class LazySingleton06 {

    private static Logger logger = LoggerFactory.getLogger(LazySingleton06.class);


    private LazySingleton06(){}

    public enum LazySingletonEnum{
        INSTANCE;

        private final LazySingleton06 instance;

        LazySingletonEnum() {
            instance = new LazySingleton06();
        }

        public LazySingleton06 getInstance() {
            return instance;
        }
    }

    public static void main(String[] args) {
        IntStream.range(0,100).forEach(num->{
            new Thread(()->{
                System.out.println(LazySingletonEnum.INSTANCE.getInstance().hashCode());
            }).start();
        });
    }
}
