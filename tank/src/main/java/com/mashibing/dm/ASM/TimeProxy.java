package com.mashibing.dm.ASM;

import java.util.Optional;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/26 0:13
 * @version: 1.0
 ***********************/
public class TimeProxy {
    public static void before() {
        Optional.of("before...").ifPresent(System.out::println);
    }
}
