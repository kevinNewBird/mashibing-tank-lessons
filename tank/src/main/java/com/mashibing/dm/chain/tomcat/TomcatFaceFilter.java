package com.mashibing.dm.chain.tomcat;

import com.mashibing.dm.chain.demo.ForumMessage;
import com.mashibing.dm.chain.demo.GlobalFilter;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:42
 * @version: 1.0
 ***********************/
public class TomcatFaceFilter implements ITomcatFilter {


    @Override
    public boolean doFilter(HttpRequest request, HttpResponse response, TomcatFilterChain chain) {
        request.str = request.str
                .replace(":)", "^^") + "3";
        chain.doFilter(request, response);
        response.str += "3";
        return true;
    }
}
