package com.mashibing.dm.state.demo2.pattern.impl;

import com.mashibing.dm.state.demo2.pattern.base.LeaveRequestModel;

import java.util.Scanner;

/**
 * description：部门经理状态对象
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 17:39
 */
public class DepManagerState implements LeaveRequestState{

    @Override
    public void doWork(StateMachine ctx) {
        // 1.先把业务对象造型回来
        LeaveRequestModel lrm = (LeaveRequestModel) ctx.getBusinessVO();

        // 2.业务处理，把审核结果保存到数据库中
        // 模拟用户处理洁面，通过控制台来读取数据
        System.out.println("部门经理审核中，请稍候......");
        System.out.printf("%s申请从%s开始请假%s天， 请部门经理审核（1为同意，2为不同意）：\n", lrm.getUser()
                , lrm.getBeginDate(), lrm.getLeaveDays());

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            int a = scanner.nextInt();
            String result = a == 1 ? "同意" : "不同意";

            lrm.setResult("部门经理审核结果：" + result);

            // 3.部门经理审核后，直接转向审核结束状态了
            ctx.setState(new AuditOverState());
            // 4.给申请人增加一个工作，让他查看审核结果
            ctx.doWork();
        }
    }
}
