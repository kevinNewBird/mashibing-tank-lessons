package com.mashibing.dm.chain.tomcat;

import com.mashibing.dm.chain.demo.ForumMessage;
import com.mashibing.dm.chain.demo.GlobalFilter;

/***********************
 * @Description: 敏感词过滤器 <BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:27
 * @version: 1.0
 ***********************/
public class TomcatSensitiveFilter implements ITomcatFilter {


    @Override
    public boolean doFilter(HttpRequest request, HttpResponse response, TomcatFilterChain chain) {
        request.str = request.str
                .replace("996", "995")
                + "2";

        chain.doFilter(request, response);
        response.str += "2";
        return true;
    }
}
