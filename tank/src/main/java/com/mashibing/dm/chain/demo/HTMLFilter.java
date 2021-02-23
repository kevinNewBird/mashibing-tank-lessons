package com.mashibing.dm.chain.demo;

/***********************
 * @Description: 富文本过滤器 <BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:26
 * @version: 1.0
 ***********************/
public class HTMLFilter implements GlobalFilter<ForumMessage> {

    @Override
    public boolean doFilter(ForumMessage entity) {
        String r = entity.getMsg();
        r = r.replace("<", "[")
                .replace(">", "]");
        entity.setMsg(r);
        return true;
    }
}
