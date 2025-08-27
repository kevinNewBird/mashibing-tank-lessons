package com.mashibing.dm.state.demo1.pattern.v2.impl;

/**
 * description： 封装一个投票状态相关的行为
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 15:52
 */
public interface VoteState {

    /**
     * 处理状态对应的行为
     * @param user： 投票人
     * @param voteItem： 投票项
     * @param voteManager： 投票上下文，用来在实现状态对应的功能处理的时候，可以回调上下文的数据
     */
    public void vote(String user, String voteItem, VoteManager voteManager);
}
