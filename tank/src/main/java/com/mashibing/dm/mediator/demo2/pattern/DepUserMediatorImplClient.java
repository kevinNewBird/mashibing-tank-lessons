package com.mashibing.dm.mediator.demo2.pattern;

import com.mashibing.dm.mediator.demo2.base.Dep;
import com.mashibing.dm.mediator.demo2.base.User;

public class DepUserMediatorImplClient {

    public static void main(String[] args) {
        // 1.准备
        // 1.1.准备要撤销的部门，仅仅需要一个部门编号
        Dep d1 = new Dep();
        d1.setDepId("d1");
        Dep d2 = new Dep();
        d2.setDepId("d2");
        // 1.2.准备用于测试的人员，也只需要一个人员编号
        User user = new User();
        user.setUserId("u1");

        // 2.测试撤销部门，在运行之前，输出一下，看这个人员属于哪些部门
        System.out.println("撤销部门前------------------------");
        DepUserMediatorImpl mediator = DepUserMediatorImpl.getInstance();
        mediator.showUserDeps(user);

        // 2.1.真正执行业务，撤销这个部门
        d1.deleteDep();

        // 2.2.再次输出一下，看这个人员属于哪些部门
        System.out.println("撤销部门后----------------------");
        mediator.showUserDeps(user);

        // 3.测试人员离职
        System.out.println("人员离职前------------------------");
        mediator.showDepUsers(d2);
        // 3.1.真正执行业务，人员离职
        user.dimission();
        // 3.2.再次输出，看这个部门下都有哪些人员
        System.out.println("人员离职后----------------------");
        mediator.showDepUsers(d2);
    }
}
