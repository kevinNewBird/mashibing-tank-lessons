package com.mashibing.dm.flyweight.demo1.source.impl;

import com.mashibing.dm.flyweight.demo1.base.TestDB;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * description： 安全管理，实现成单例
 *
 * @author zhaosong
 * @version 1.0
 * @company 北京海量数据有限公司
 * @date 2025/8/29 14:54
 */
public class SecurityMgr {

    /**
     * 在运行期间，用来存放登录人员对应的权限
     * 在web应用中，这些数据通常会存放到session中
     */
    private Map<String, Collection<AuthorizationModel>> AUTHS = new HashMap<>();

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
        Collection<AuthorizationModel> models = queryByUser(user);
        AUTHS.put(user, models);
    }

    /**
     * 判断用户对某个安全实体是否拥有某种权限
     *
     * @param user：               被检测权限的用户
     * @param securityEntity：安全实体
     * @param permit：权限
     * @return true 表示拥有相应权限，false表示没有相应权限
     */
    public boolean hasPermit(String user, String securityEntity, String permit) {

        Collection<AuthorizationModel> models = AUTHS.get(user);
        if (CollectionUtils.isEmpty(models)) {
            System.out.printf("%s没有登录或是没有被分配任何权限\n", user);
            return false;
        }

        // 循环遍历判断当前用户拥有的已授权的数据对象
        for (AuthorizationModel permitModel : models) {
            // 输出当前实例，看看是否同一个实例对象
            System.out.println("permitModel == " + permitModel);
            if (StringUtils.equals(permitModel.getSecurityEntity(), securityEntity)
                    && StringUtils.equals(permitModel.getPermit(), permit)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 从数据库中获取某人所拥有的权限
     *
     * @param user： 需要获取所拥有的权限的人员
     * @return： 某人所拥有的权限
     */
    private Collection<AuthorizationModel> queryByUser(String user) {
        List<AuthorizationModel> models = new ArrayList<>();
        for (String permitS : TestDB.dbData) {
            // permitS:   李四,人员列表,修改
            String[] permitArray = StringUtils.splitByWholeSeparator(permitS, ",");
            if (StringUtils.equals(permitArray[0], user)) {
                AuthorizationModel model = new AuthorizationModel();
                model.setUser(permitArray[0]);
                model.setSecurityEntity(permitArray[1]);
                model.setPermit(permitArray[2]);

                models.add(model);
            }
        }

        return models;
    }

    private static class InstanceHolder {
        public static final SecurityMgr INSTANCE = new SecurityMgr();
    }
}
