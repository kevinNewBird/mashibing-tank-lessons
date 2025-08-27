package com.mashibing.dm.state.demo1.pattern.v2.impl;

/**
 * description：恶意投票
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 16:00
 */
public class SpiteVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 恶意投票：取消用户投票资格，并取消投票记录
        String s = voteManager.getMapVote().get(user);
        if (s != null) {
            voteManager.getMapVote().remove(user);
        }
        System.out.println("======>你有恶意刷票行为，取消投票资格");

        //  恶意投票完成，维护下一个状态，恶意投票到8次，就算上黑名单了
        if (voteManager.getMapVoteCount().get(user) >= 7) {
            voteManager.getMapState().put(user, new BlackVoteState());
        }
    }
}
