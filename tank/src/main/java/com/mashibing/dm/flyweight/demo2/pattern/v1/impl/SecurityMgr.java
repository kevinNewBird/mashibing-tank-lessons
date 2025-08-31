package com.mashibing.dm.flyweight.demo2.pattern.v1.impl;

import com.mashibing.dm.flyweight.demo2.base.TestDB;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * description：安全管理，实现成单例
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 23:11
 */
public class SecurityMgr {

    /**
     * 在运行期间，用来存放登录人员对应的权限
     * 在Web应用中，这些数据通常会存放到session中
     */
    private Map<String, Collection<FlyWeight>> hasLoginAuths = new HashMap<>();

    private SecurityMgr() {

    }

    public static SecurityMgr getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 模拟登录的功能
     *
     * @param user: 登录的用户
     */
    public void login(String user) {
        // 登录时就需要把该用户所拥有的权限，从数据库中取出来，放到缓存中去
        Collection<FlyWeight> flyWeights = queryByUser(user);
        hasLoginAuths.put(user, flyWeights);
    }

    /**
     * 判断某用户对某个安全实体是否具有某种权限
     *
     * @param user:           被检测权限的用户
     * @param securityEntity： 安全实体
     * @param permit：         权限
     * @return
     */

    public boolean hasPermit(String user, String securityEntity, String permit) {
        Collection<FlyWeight> flyWeights = hasLoginAuths.get(user);

        System.out.printf("现在测试%s的权限%s，map.size = %s\n", securityEntity, permit, hasLoginAuths.size());
        if (CollectionUtils.isEmpty(flyWeights)) {
            System.out.printf("%s没有登录或是没有被分配任何权限\n", user);
            return false;
        }

        for (FlyWeight fm : flyWeights) {
            // 输出当前实例，看看是否同一个实例对象
            System.out.println("fm == " + fm);
            if (fm.match(securityEntity, permit)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 从数据库中获取某人所拥有的权限
     *
     * @param user： 需要获取所拥有的权限人员
     * @return 某人所拥有的劝降
     */
    private Collection<FlyWeight> queryByUser(String user) {
        Collection<FlyWeight> flyWeights = new ArrayList<>();

        for (String permitS : TestDB.dbData) {
            String[] permitArray = StringUtils.splitByWholeSeparator(permitS, ",");
            if (StringUtils.equals(permitArray[0], user)) {
                String flag = permitArray[3]; // 标识：是否是组合数据
                FlyWeight fm;
                if (StringUtils.equals(flag, "1")) {
                    fm = FlyweightFactory.getInstance().getFlyweight(String.format("%s,%s",
                            permitArray[1], permitArray[2]));
                } else {
                    // 组合对象
                    fm = new UnsharedConcreteFlyweight();
                    // 获取需要组合的数据（操作者薪资数据）
                    String[] composite = TestDB.mapData.get(permitArray[1]);
                    for (String tmpS : composite) {
                        FlyWeight tempFm = FlyweightFactory.getInstance().getFlyweight(tmpS);
                        // 把这个对象加入到组合对象中
                        fm.add(tempFm);
                    }
                }

                flyWeights.add(fm);
            }
        }

        return flyWeights;
    }

    private static class InstanceHolder {
        public static final SecurityMgr INSTANCE = new SecurityMgr();
    }
}
