package com.mashibing.dm.flyweight.demo1.source;

import com.mashibing.dm.flyweight.demo1.source.impl.SecurityMgr;

/**
 * description：登录客户端
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 16:04
 */
public class LoginClient {


    public static void main(String[] args) {
        // 1.需要先登录，然后再判断是否有权限
        SecurityMgr mgr = SecurityMgr.getInstance();
        mgr.login("张三");
        mgr.login("李四");
        boolean f1 = mgr.hasPermit("张三", "薪资数据", "查看");
        boolean f2 = mgr.hasPermit("李四", "薪资数据", "查看");

        System.out.println("f1 == " + f1);
        System.out.println("f2 == " + f2);
        for (int i = 0; i < 3; i++) {
            mgr.login("张三" + i);
            mgr.hasPermit("张三" + i, "薪资数据", "查看");
        }

        /**
         * permitModel == AuthorizationModel(user=张三, securityEntity=人员列表, permit=查看)
         * permitModel == AuthorizationModel(user=李四, securityEntity=人员列表, permit=查看)
         * permitModel == AuthorizationModel(user=李四, securityEntity=薪资数据, permit=查看)
         * f1 == false
         * f2 == true
         * permitModel == AuthorizationModel(user=张三0, securityEntity=人员列表, permit=查看)
         * permitModel == AuthorizationModel(user=张三1, securityEntity=人员列表, permit=查看)
         * permitModel == AuthorizationModel(user=张三2, securityEntity=人员列表, permit=查看)
         */
    }
}
