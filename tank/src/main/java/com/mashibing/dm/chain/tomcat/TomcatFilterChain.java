package com.mashibing.dm.chain.tomcat;

import com.mashibing.dm.chain.demo.FilterChain;

import java.util.ArrayList;
import java.util.List;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/23 22:05
 * @version: 1.0
 ***********************/
public class TomcatFilterChain{

    int index = 0;

    List<ITomcatFilter> filters = new ArrayList<>();

    public TomcatFilterChain addFilter(ITomcatFilter filter) {
        filters.add(filter);
        return this;
    }


    public void doFilter(HttpRequest request, HttpResponse response) {
        if (index == filters.size()){
            return;
        }
        ITomcatFilter f = filters.get(index++);
        f.doFilter(request, response, this);
    }

}
