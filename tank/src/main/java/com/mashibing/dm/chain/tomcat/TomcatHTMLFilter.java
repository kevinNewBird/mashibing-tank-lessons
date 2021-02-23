package com.mashibing.dm.chain.tomcat;

import com.mashibing.dm.chain.demo.ForumMessage;
import com.mashibing.dm.chain.demo.GlobalFilter;

/***********************
 * @Description: 富文本过滤器 <BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:26
 * @version: 1.0
 ***********************/
public class TomcatHTMLFilter implements ITomcatFilter {


    @Override
    public boolean doFilter(HttpRequest request, HttpResponse response, TomcatFilterChain chain) {
        request.str = request.str
                .replace("<", "[")
                .replace(">", "]")
                + "1";
        chain.doFilter(request, response);
        response.str += "1";
        return true;
    }
}
