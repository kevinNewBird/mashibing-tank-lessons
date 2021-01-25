package com.mashibing.tank.frame;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/25 23:15
 * @version: 1.0
 ***********************/
public interface FireStrategy<T> {

    void apply(T t);
}
