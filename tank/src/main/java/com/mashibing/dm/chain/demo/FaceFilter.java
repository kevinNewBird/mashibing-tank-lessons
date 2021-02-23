package com.mashibing.dm.chain.demo;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/2/20 1:42
 * @version: 1.0
 ***********************/
public class FaceFilter implements GlobalFilter<ForumMessage>{

    @Override
    public boolean doFilter(ForumMessage entity) {
        String r = entity.getMsg();
        r = r.replace(":)", "^.^");
        entity.setMsg(r);
        return true;
    }
}
