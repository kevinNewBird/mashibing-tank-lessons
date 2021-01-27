package com.mashibing.dm.singleton;

import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: 懒汉式<BR>
 *      针对线程不安全的问题,增加了区块锁,但无法确保线程的安全
 * author: zhao.song
 * date: 2021/1/25 14:21
 * version: 1.0
 ***********************/
public class LazySingleton03 {

    private static Logger logger = LoggerFactory.getLogger(LazySingleton03.class);

    private static LazySingleton03 INSTANCE;

    private LazySingleton03(){}

    public static LazySingleton03 getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton03.class) {
                Try.run(()->TimeUnit.MILLISECONDS.sleep(1))
                        .onFailure(e->logger.error("线程休眠发生异常!",e));
                INSTANCE = new LazySingleton03();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        IntStream.range(0,100).forEach(num->{
            new Thread(()->{
                System.out.println(LazySingleton03.getInstance().hashCode());
            }).start();
        });
    }
}