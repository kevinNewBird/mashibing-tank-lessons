package com.mashibing.dm.singleton;

import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: 懒汉式<BR>
 *      虽然达到了按需初始化的目的,但却带来线程哼不安全的问题
 * author: zhao.song
 * date: 2021/1/25 14:21
 * version: 1.0
 ***********************/
public class LazySingleton01 {

    private static Logger logger = LoggerFactory.getLogger(LazySingleton01.class);

    private static LazySingleton01 INSTANCE;

    private LazySingleton01(){}

    public static LazySingleton01 getInstance() {
        if (INSTANCE == null) {
            Try.run(()->TimeUnit.SECONDS.sleep(1))
                    .onFailure(e->logger.error("线程休眠发生异常!",e));
            INSTANCE = new LazySingleton01();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        IntStream.range(0,100).forEach(num->{
            new Thread(()->{
                System.out.println(LazySingleton01.getInstance().hashCode());
            }).start();
        });
    }
}
