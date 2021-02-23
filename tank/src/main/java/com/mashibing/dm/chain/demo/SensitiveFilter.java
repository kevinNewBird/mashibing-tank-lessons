package com.mashibing.dm.chain.demo;

/***********************
 * @Description: 敏感词过滤器 <BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:27
 * @version: 1.0
 ***********************/
public class SensitiveFilter implements GlobalFilter<ForumMessage> {
    @Override
    public boolean doFilter(ForumMessage entity) {
        String r = entity.getMsg();
        r = r.replace("996", "955");
        entity.setMsg(r);
        return false;
    }
}
