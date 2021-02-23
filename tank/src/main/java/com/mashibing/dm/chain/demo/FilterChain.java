package com.mashibing.dm.chain.demo;

import java.util.ArrayList;
import java.util.List;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:36
 * @version: 1.0
 ***********************/
public class FilterChain<T> implements GlobalFilter<T> {

    List<GlobalFilter<T>> filters = new ArrayList<>();

    public FilterChain add(GlobalFilter<T> filter) {
        filters.add(filter);
        return this;
    }

    public boolean doFilter(T m) {
        for (GlobalFilter<T> filter : filters) {
            if (!filter.doFilter(m)) {
                return false;
            }
        }
        return true;
    }
}
