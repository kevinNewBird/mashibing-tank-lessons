package com.mashibing.dm.state.demo1.source;

import com.mashibing.dm.state.demo1.source.impl.VoteManager;

/**
 * description：投票客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 15:39
 */
public class VoteClient {

    public static void main(String[] args) {
        VoteManager vm = new VoteManager();
        for (int i = 0; i < 8; i++) {
            vm.vote("u1", "A");
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
