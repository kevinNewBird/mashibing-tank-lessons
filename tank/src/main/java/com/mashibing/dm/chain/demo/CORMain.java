package com.mashibing.dm.chain.demo;

import java.util.ArrayList;
import java.util.List;

/***********************
 * @Description: 责任链客户端 (缩写:cor,即Chain of Responsibility) <BR>
 * @author: zhao.song
 * @since: 2021/2/20 0:20
 * @version: 1.0
 ***********************/
public class CORMain {

    public static void main(String[] args) {
        ForumMessage msg = new ForumMessage();
        msg.setMsg("大家好:),<script>,环境访问 mashibing.com,大家都是996...");

        //version1.0
//        dealByFilter(msg);

        //version2.0:模拟责任链
//        dealByFilterList(msg);

        //version3.0:
//        doFilterByChain(msg);

        //version4.0
        doFilterByChains(msg);

        System.out.println(msg);
    }

    /**
     * Description: version1.0 过滤器方式处理 [富文本标签] 和 [敏感词] <BR>
     *
     * @param msg:
     * @return
     * @author zhao.song    2021/2/20 1:31
     */
    public static void dealByFilter(ForumMessage msg) {
        //处理msg: 敏感词,不友好代码
        new HTMLFilter().doFilter(msg);
        new SensitiveFilter().doFilter(msg);
    }


    /**
     * Description: version2.0 使用过滤器集合,过滤器方式处理 [富文本标签] 和 [敏感词] <BR>
     *
     * @param msg:
     * @return
     * @author zhao.song    2021/2/20 1:31
     */
    public static void dealByFilterList(ForumMessage msg) {

        List<GlobalFilter<ForumMessage>> list = new ArrayList<>();
        list.add(new HTMLFilter());
        list.add(new SensitiveFilter());
        //处理msg: 敏感词,不友好代码
        for (GlobalFilter<ForumMessage> filter : list) {
            filter.doFilter(msg);
        }
    }

    /**
     * Description: version3.0 使用责任链,过滤器方式处理 [富文本标签] 和 [敏感词]  <BR>
     *
     * @author zhao.song    2021/2/20 1:45
     * @param msg:
     * @return
     */
    public static void doFilterByChain(ForumMessage msg) {
        FilterChain<ForumMessage> chain = new FilterChain<>();
        chain.add(new HTMLFilter())
                .add(new SensitiveFilter());
        chain.doFilter(msg);
    }

    /**
     * Description: version4.0 使用责任链(并将多个责任链合并),过滤器方式处理 [富文本标签] 和 [敏感词]  <BR>
     *
     * @author zhao.song    2021/2/20 1:45
     * @param msg:
     * @return
     */
    public static void doFilterByChains(ForumMessage msg) {
        FilterChain<ForumMessage> chain0 = new FilterChain<>();
        chain0.add(new HTMLFilter());
        FilterChain<ForumMessage> chain1 = new FilterChain<>();
        chain1.add(new FaceFilter())
                .add(new SensitiveFilter());
        chain0.add(chain1);

        chain0.doFilter(msg);
    }
}
