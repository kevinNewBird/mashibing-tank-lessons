package com.mashibing.tank.frame;

import java.io.Serializable;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/1/25 23:15
 * @version: 1.0
 ***********************/
public interface FireStrategy<T> extends Serializable {

    void apply(T t);
}
