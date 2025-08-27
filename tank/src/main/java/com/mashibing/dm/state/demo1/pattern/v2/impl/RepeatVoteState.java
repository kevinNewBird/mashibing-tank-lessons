package com.mashibing.dm.state.demo1.pattern.v2.impl;

/**
 * description：重复投票
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 15:59
 */
public class RepeatVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 重复投票：暂时不做处理
        System.out.println("======>请不要重复投票");

        // 重复投票完成，维护下一状态，重复投票到5次，就算恶意投票了
        // 注意这里是判断大于等于4，因为这里设置的事下一个状态
        // 下一个操作次数就是5了，就应该算是恶意投票了
        if (voteManager.getMapVoteCount().get(user) >= 4) {
            voteManager.getMapState().put(user, new SpiteVoteState());
        }

    }
}
