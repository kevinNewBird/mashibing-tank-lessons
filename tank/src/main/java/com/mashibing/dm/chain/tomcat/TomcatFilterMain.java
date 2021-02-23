package com.mashibing.dm.chain.tomcat;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/23 22:14
 * @version: 1.0
 ***********************/
public class TomcatFilterMain {

    public static void main(String[] args) {
        TomcatFilterChain chain = new TomcatFilterChain();
        chain.addFilter(new TomcatHTMLFilter())
                .addFilter(new TomcatSensitiveFilter())
                .addFilter(new TomcatUrlFilter());
        HttpRequest req = new HttpRequest();
        HttpResponse resp = new HttpResponse();
        req.str = "大家好:),<script>,环境访问 mashibing.com,大家都是996...";
        resp.str = "";
        chain.doFilter(req, resp);
        System.out.println(req.str);
        System.out.println(resp.str);

    }
}
