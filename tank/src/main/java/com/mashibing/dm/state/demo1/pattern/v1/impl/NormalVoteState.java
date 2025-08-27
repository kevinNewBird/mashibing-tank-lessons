package com.mashibing.dm.state.demo1.pattern.v1.impl;

/**
 * description：正常投票状态
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 15:57
 */
public class NormalVoteState implements VoteState{

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 正常投票：记录到投票记录中
        voteManager.getMapVote().put(user, voteItem);
        System.out.println("======>恭喜你投票成功");
    }
}
