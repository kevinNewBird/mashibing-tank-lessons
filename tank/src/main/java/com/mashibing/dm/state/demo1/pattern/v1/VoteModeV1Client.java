package com.mashibing.dm.state.demo1.pattern.v1;

import com.mashibing.dm.state.demo1.pattern.v1.impl.VoteManager;

/**
 * description： 投票客户端（模式）
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 16:08
 */
public class VoteModeV1Client {

    public static void main(String[] args) {
        VoteManager vm = new VoteManager();
        for (int i = 0; i < 8; i++) {
            vm.vote("u2", "B");
        }

        /**
         * ======>恭喜你投票成功
         * ======>请不要重复投票
         * ======>请不要重复投票
         * ======>请不要重复投票
         * ======>你有恶意刷票行为，取消投票资格
         * ======>你有恶意刷票行为，取消投票资格
         * ======>你有恶意刷票行为，取消投票资格
         * ======>进入黑名单，将禁止登录和使用本系统
         */
    }
}
