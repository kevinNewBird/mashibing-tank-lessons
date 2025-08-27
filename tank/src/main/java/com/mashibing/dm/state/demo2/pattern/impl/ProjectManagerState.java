package com.mashibing.dm.state.demo2.pattern.impl;

import com.mashibing.dm.state.demo2.pattern.base.LeaveRequestModel;
import org.apache.commons.lang.StringUtils;

import java.util.Scanner;

/**
 * description：项目经理审核的状态类
 * 处理后可能对应部门经理审核或者审核结束之中的一种
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/27 17:35
 */
public class ProjectManagerState implements LeaveRequestState {

    @Override
    public void doWork(StateMachine ctx) {
        // 1.先把业务对象造型回来
        LeaveRequestModel lrm = (LeaveRequestModel) ctx.getBusinessVO();

        // 2.业务处理，把审核结果保存到数据库
        // 模拟用户处理洁面，通过控制台来读取数据
        System.out.println("项目经理审核中，请稍候......");
        System.out.printf("%s申请从%s开始请假%s天， 请项目经理审核（1为同意，2为不同意）：\n", lrm.getUser()
                , lrm.getBeginDate(), lrm.getLeaveDays());

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            int a = scanner.nextInt();
            // 设置回到上下文中
            String result = a == 1 ? "同意" : "不同意";

            lrm.setResult("项目经理审核结果：" + result);

            // 3.根据选择的结果和条件来设置下一步
            if (a == 1) {
                if (lrm.getLeaveDays() > 3) {
                    // 如果请假天数大于3天，而且项目经理同意了，就提交给部门经理
                    ctx.setState(new DepManagerState());
                    // 给部门经理增加一个工作
                    ctx.doWork();
                } else {
                    // 3天以内的请假，由项目经理决定, 转向审核结束状态
                    ctx.setState(new AuditOverState());
                    //  给申请人增加一个工作，让他查看审核结果
                    ctx.doWork();
                }
            } else {
                //  项目经理不同意，直接结束，转向审核结束状体啊
                ctx.setState(new AuditOverState());
                //  给申请人增加一个工作，让他查看审核结果
                ctx.doWork();
            }
        }
    }
}
