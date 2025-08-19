package com.mashibing.dm.mediator.demo2.pattern;

import com.mashibing.dm.mediator.demo2.base.Dep;
import com.mashibing.dm.mediator.demo2.base.DepUserModel;
import com.mashibing.dm.mediator.demo2.base.User;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现部门和人员交互的中介者实现类
 * <br/>
 * 说明：为了演示的简洁性，只示例实现撤销部门和人员离职的功能
 */
public class DepUserMediatorImpl {

    private DepUserMediatorImpl() {
        initTestData();
    }

    private static class SingletonHolder {
        private static final DepUserMediatorImpl INSTANCE = new DepUserMediatorImpl();
    }

    public static DepUserMediatorImpl getInstance() {
        return DepUserMediatorImpl.SingletonHolder.INSTANCE;
    }

    /**
     * 测试用，记录部门和人员的关系
     */
    private Collection<DepUserModel> depUserCol = new ArrayList<>();

    /**
     * 初始化测试数据
     */
    private void initTestData() {
        for (int i = 1; i <= 5; i++) {
            DepUserModel du = new DepUserModel().setDepUserId("du" + i)
                    .setDepId("d" + (i > 2 ? 2 : 1))
                    .setUserId("u" + (i > 4 ? 1 : i));
            depUserCol.add(du);
        }
    }

    /**
     * 完成因撤销部门的操作所引起的与人员的交互，需要去除相应的关系
     *
     * @param depId：被撤销的部门对象的编号
     * @return：是否已经正确处理了因撤销部门所引起的与人员的交互
     */
    public boolean deleteDep(String depId) {
        // 请注意：为了演示简单，部门撤销后，原部门的人员怎么处理等后续业务处理就不管了

        // 1.到记录部门和人员关系的集合里面，寻找跟这个部门相关的人员
        // 设置一个临时的集合，记录需要清除的关系对象。作为历史记录（todo）
        List<DepUserModel> tempCol = depUserCol.stream().filter(du -> StringUtils.equals(du.getDepId(), depId))
                .collect(Collectors.toList());

        // 2.从关系集合里清除掉这些关系
        depUserCol.removeAll(tempCol);

        return true;
    }


    /**
     * 完成因人员离职引起的与部门的交互
     *
     * @param userId：离职人员的编号
     * @return：是否正确处理了因人员离职引起的与部门的交互
     */
    public boolean deleteUser(String userId) {
        // 1.到记录部门和人员关系的集合里面，寻找跟这个人员相关的部门
        // 设置一个临时的集合，记录需要清除的关系对象。作为历史记录（todo）
        List<DepUserModel> tempCol = depUserCol.stream().filter(du -> StringUtils.equals(du.getUserId(), userId))
                .collect(Collectors.toList());

        // 2.从关系集合里清除掉这些关系
        depUserCol.removeAll(tempCol);
        return true;
    }


    /**
     * 测试用，在内部打印显示一个部门下的所有人员
     *
     * @param dep
     */
    public void showDepUsers(Dep dep) {
        depUserCol.stream().filter(du -> StringUtils.equals(du.getDepUserId(), dep.getDepId()))
                .forEach(du -> {
                    System.out.printf("部门编号=%s下面拥有人员，其编号是：%s\n", du.getDepId(), du.getUserId());
                });
    }


    /**
     * 测试用，在内部打印显示一个人员所属的部门
     *
     * @param user
     */
    public void showUserDeps(User user) {
        depUserCol.stream().filter(du -> StringUtils.equals(du.getUserId(), user.getUserId()))
                .forEach(du -> {
                    System.out.printf("人员编号=%s属于部门编号是：%s\n", du.getUserId(), du.getDepId());
                });
    }

}
