package com.mashibing.dm.singleton;

import java.util.stream.IntStream;

/***********************
 * Description: 饿汉式 <BR>
 *     类加载到内存后,就实例化一个单例, JVM保证线程安全
 *     简单使用,推荐使用!
 *     唯一缺点:不管用到与否,类装载时就完成实例化
 * author: zhao.song
 * date: 2021/1/25 14:14
 * version: 1.0
 ***********************/
public class HungrySingleton02 {

    private static final HungrySingleton02 INSTANCE;

    static {
        INSTANCE = new HungrySingleton02();
    }

    private HungrySingleton02(){}

    public static HungrySingleton02 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        IntStream.range(0,100).forEach(num->{
            new Thread(()->{
                System.out.println(HungrySingleton02.getInstance().hashCode());
            }).start();
        });
    }
}
