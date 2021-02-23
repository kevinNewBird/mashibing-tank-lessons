package com.mashibing.dm.chain.tomcat;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:42
 * @version: 1.0
 ***********************/
public class TomcatUrlFilter implements ITomcatFilter {


    @Override
    public boolean doFilter(HttpRequest request, HttpResponse response, TomcatFilterChain chain) {
        request.str = request.str
                .replace("mashibing.com", "www.baidu.com")
                + "4";
        chain.doFilter(request, response);
        response.str += "4";
        return true;
    }
}
