package com.mashibing.dm.state.demo1.pattern.v2.impl;

/**
 * description：黑名单
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 16:03
 */
public class BlackVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 黑名单：加入黑名单中，禁止登录系统了
        System.out.println("======>进入黑名单，将禁止登录和使用本系统");
    }
}
