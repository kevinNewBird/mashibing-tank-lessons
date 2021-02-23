package com.mashibing.dm.chain.demo;

/***********************
 * @Description: 过滤器<BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:23
 * @version: 1.0
 ***********************/
public interface GlobalFilter<T> {
    /**
     * Description: 过滤器 <BR>
     *
     * @author zhao.song    2021/2/20 1:25
     * @param entity:
     * @return:boolean 主要用于控制责任链模式是否继续往下执行
     */
    boolean doFilter(T entity);
}
