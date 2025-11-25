package com.mashibing.dm.state.demo1.pattern.v2.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * description：投票管理器
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 15:53
 */
public class VoteManager {

    private VoteState state;

    /**
     * 记录转换状态
     * Map<String,String>对应Map<用户名称,下一转换状态>
     */
    private Map<String, VoteState> mapState = new HashMap<>();

    /**
     * 记录用户投票的结果
     * Map<String,String>对应Map<用户名称,投票的选项>
     */
    private Map<String, String> mapVote = new HashMap<>();

    /**
     * 记录用户投票次数
     * Map<String,Integer>对应Map<用户名称,投票的次数>
     */
    private Map<String, Integer> mapVoteCount = new HashMap<>();

    /**
     * 获取记录用户投票结果的Map
     * @return
     */
    public Map<String, String> getMapVote() {
        return mapVote;
    }

    /**
     * 获取记录每个用户对应的状态处理对象的Map
     * @return
     */
    public Map<String, VoteState> getMapState() {
        return mapState;
    }

    /**
     * 获取记录每个用户对应的投票次数的map
     * @return
     */
    public Map<String, Integer> getMapVoteCount() {
        return mapVoteCount;
    }

    /**
     * 投票
     * @param user：投票人
     * @param voteItem：投票的选项
     */
    public void vote(String user, String voteItem) {
        // todo 1.先为该用户增加投票的次数，可以考虑抽取到状态实现中
        // todo 如需放在外面需考虑值溢出的问题，对值最大值进行限制，避免恶意突破底层逻辑
        // 从记录中取出已有的投票次数
        Integer oldVoteCount = mapVoteCount.get(user);
        if (null == oldVoteCount) {
            oldVoteCount = 0;
        }
        oldVoteCount = oldVoteCount + 1;
        mapVoteCount.put(user, oldVoteCount);

        // 2.获取该用户的投票状态
        state = mapState.get(user);
        // 如果没有投票状态，说明还没有投过票，就初始化一个正常投票状态
        if (state == null) {
            state = new NormalVoteState();
        }

        // 3.然后转调状态对象来进行相应的操作
        state.vote(user, voteItem, this);
    }
}
