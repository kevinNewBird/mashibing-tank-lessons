package com.mashibing.dm.ASM;

import java.util.Optional;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/23 17:23
 * @version: 1.0
 ***********************/
public class Tank {

    public void move() {
        Optional.of("the tank moving calacalacala....").ifPresent(System.out::println);
    }
}
