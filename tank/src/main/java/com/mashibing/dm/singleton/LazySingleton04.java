package com.mashibing.dm.singleton;

import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: 懒汉式:双重校验锁<BR>
 *      针对线程不安全的问题,双重校验锁
 * author: zhao.song
 * date: 2021/1/25 14:21
 * version: 1.0
 ***********************/
public class LazySingleton04 {

    private static Logger logger = LoggerFactory.getLogger(LazySingleton04.class);

    private static LazySingleton04 INSTANCE;

    private LazySingleton04(){}

    public static LazySingleton04 getInstance() {
        if (INSTANCE == null) {
            //双重检查(不加锁会导致多实例化)
            synchronized (LazySingleton04.class) {
                Try.run(()->TimeUnit.SECONDS.sleep(1))
                        .onFailure(e->logger.error("线程休眠发生异常!",e));
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton04();
                }
            }

        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        IntStream.range(0,100).forEach(num->{
            new Thread(()->{
                System.out.println(LazySingleton04.getInstance().hashCode());
            }).start();
        });
    }
}
