package com.mashibing.dm.state.demo1.pattern.v1.impl;

/**
 * description：重复投票
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 15:59
 */
public class RepeatVoteState implements VoteState{

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 重复投票：暂时不做处理
        System.out.println("======>请不要重复投票");
    }
}
