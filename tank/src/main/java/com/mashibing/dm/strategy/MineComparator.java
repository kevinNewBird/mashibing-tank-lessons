package com.mashibing.dm.strategy;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @date: 2021/1/25 22:30
 * @version: 1.0
 ***********************/
public interface MineComparator<T> {

    //比较策略
    int compare(T o1,T o2);
}
