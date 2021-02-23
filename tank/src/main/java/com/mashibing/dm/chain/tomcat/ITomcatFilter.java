package com.mashibing.dm.chain.tomcat;

/***********************
 * @Description: 模拟servlet的过滤链 <BR>
 * @author: zhao.song
 * @since: 2021/2/23 22:04
 * @version: 1.0
 ***********************/
public interface ITomcatFilter {

    boolean doFilter(HttpRequest request, HttpResponse response, TomcatFilterChain chain);
}
