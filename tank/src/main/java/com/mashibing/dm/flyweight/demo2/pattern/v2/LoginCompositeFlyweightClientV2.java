package com.mashibing.dm.flyweight.demo2.pattern.v2;

import com.mashibing.dm.flyweight.demo2.pattern.v2.impl.FlyweightFactory;
import com.mashibing.dm.flyweight.demo2.pattern.v2.impl.SecurityMgr;

/**
 * description：登录客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 16:04
 */
public class LoginCompositeFlyweightClientV2 {


    public static void main(String[] args) {
        // 1.需要先登录，然后再判断是否有权限
        SecurityMgr mgr = SecurityMgr.getInstance();
        boolean f1 = mgr.hasPermit("张三", "薪资数据", "查看");
        boolean f2 = mgr.hasPermit("李四", "薪资数据", "查看");
        boolean f3 = mgr.hasPermit("李四", "薪资数据", "修改");

        for (int i = 0; i < 3; i++) {
            mgr.hasPermit("张三" + i, "薪资数据", "查看");
        }

        // 特别提醒，这里查看的引用次数，不是指测试使用的次数，
        // 指的是SecurityMgr的 queryUser 方法通过享元公差功能去获取享元对象的次数
        System.out.printf("薪资数据，查看 被引用了%s次\n"
                , FlyweightFactory.getInstance().getUserTimes("薪资数据,查看"));
        System.out.printf("薪资数据，修改 被引用了%s次\n"
                , FlyweightFactory.getInstance().getUserTimes("薪资数据,修改"));
        System.out.printf("人员列表，查看 被引用了%s次\n"
                , FlyweightFactory.getInstance().getUserTimes("人员列表,查看"));

        /**
         * now thread=0,fsMap==[]
         * 薪资数据，查看 被引用了2次
         * 薪资数据，修改 被引用了2次
         * 人员列表，查看 被引用了6次
         * now thread=3,fsMap==[人员列表,查看, 薪资数据,修改, 薪资数据,查看]
         * now thread=3,fsMap==[人员列表,查看, 薪资数据,修改, 薪资数据,查看]
         * now thread=3,fsMap==[人员列表,查看, 薪资数据,修改, 薪资数据,查看]
         * now thread=3,fsMap==[人员列表,查看, 薪资数据,修改, 薪资数据,查看]
         * now thread=3,fsMap==[人员列表,查看, 薪资数据,修改, 薪资数据,查看]
         * now thread=0,fsMap==[]
         */
    }
}
