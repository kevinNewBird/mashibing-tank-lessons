package com.mashibing.dm.state.demo2.pattern;

import com.mashibing.dm.state.demo2.pattern.base.LeaveRequestModel;
import com.mashibing.dm.state.demo2.pattern.impl.LeaveRequestContext;
import com.mashibing.dm.state.demo2.pattern.impl.ProjectManagerState;

/**
 * description：请假申请工作流客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 17:59
 */
public class LeaveRequestClient {

    public static void main(String[] args) {
        // 1.创建业务对象
        LeaveRequestModel lrm = new LeaveRequestModel();
        lrm.setUser("小李");
        lrm.setBeginDate("2025-08-27");
        lrm.setLeaveDays(5);

        // 2.创建上下文对象
        LeaveRequestContext lrc = new LeaveRequestContext();
        // todo 如果流程固定，可以考虑整合到构造方法中；
        // todo 使用set方法的好处是，可通过传入不同的状态实现不同身份请求者的不同工作流控制
        lrc.setBusinessVO(lrm);
        lrc.setState(new ProjectManagerState());

        // 3.请求上下文
        lrc.doWork();

        /**
         * 项目经理审核中，请稍候......
         * 小李申请从2025-08-27开始请假5天， 请项目经理审核（1为同意，2为不同意）：
         * 1
         * 部门经理审核中，请稍候......
         * 小李申请从2025-08-27开始请假5天， 请部门经理审核（1为同意，2为不同意）：
         * 2
         * 小李, 你的请假申请已经审核结束，结果是：部门经理审核结果：不同意
         */
    }
}
