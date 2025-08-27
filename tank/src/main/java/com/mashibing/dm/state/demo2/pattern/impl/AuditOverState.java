package com.mashibing.dm.state.demo2.pattern.impl;

import com.mashibing.dm.state.demo2.pattern.base.LeaveRequestModel;

/**
 * description：c审核结束状态
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 17:42
 */
public class AuditOverState implements State {
    @Override
    public void doWork(StateMachine ctx) {
        // 1.先把业务对象造型回来
        LeaveRequestModel lrm = (LeaveRequestModel) ctx.getBusinessVO();

        // 2.业务处理，在数据中记录整个流程结束
        System.out.printf("%s, 你的请假申请已经审核结束，结果是：%s\n", lrm.getUser(), lrm.getResult());
    }
}
