package com.mashibing.dm.proxy.demo1.pattern.base;


import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 代理对象
 */
public class UserModelProxy implements UserModelApi {

    /**
     * 持有被代理的具体的目标对象
     */
    private UserModelImpl realSubject;

    /**
     * 标示是否已经重新装载过数据了
     */
    private boolean loaded = false;

    public UserModelProxy(UserModelImpl realSubject) {
        this.realSubject = realSubject;
    }


    @Override
    public String getUserId() {
        return realSubject.getUserId();
    }

    /**
     * setter方法不需要重新查询数据库，直接调用具体目标对象的相应功能就可以了
     */
    @Override
    public void setUserId(String userId) {
        realSubject.setUserId(userId);
    }

    @Override
    public String getUserName() {
        return realSubject.getUserName();
    }

    /**
     * setter方法不需要重新查询数据库，直接调用具体目标对象的相应功能就可以了
     */
    @Override
    public void setUserName(String userName) {
        realSubject.setUserName(userName);
    }

    @Override
    public String getDepId() {
        // 从数据库重新装载
        reload();

        return realSubject.getDepId();
    }

    /**
     * setter方法不需要重新查询数据库，直接调用具体目标对象的相应功能就可以了
     */
    @Override
    public void setDepId(String depId) {
        realSubject.setDepId(depId);
    }

    @Override
    public String getSex() {

        // 从数据库重新装载
        reload();

        return realSubject.getSex();
    }

    /**
     * setter方法不需要重新查询数据库，直接调用具体目标对象的相应功能就可以了
     */
    @Override
    public void setSex(String sex) {
        realSubject.setSex(sex);
    }

    /**
     * 重新查询数据库以获取完整的用户数据
     */
    private void reload() {
        // 需要判断是否已经装载过了
        if (!this.loaded) {
            // 从数据库重新装载
            doReload();
            // 设置重新装载的标志为true
            this.loaded = true;
        }
    }

    private void doReload() {
        System.out.printf("重新查询数据库获取完整的用户数据， userId=%s", realSubject.getUserId());

        Map<String, String> data = UserModelDao.getInstance().queryByUserId(realSubject.getUserId());
        if (MapUtils.isEmpty(data)){
            return;
        }
        realSubject.setDepId(data.get("depId"));
        realSubject.setSex(data.get("sex"));
    }

    @Override
    public String toString() {
       return realSubject.toString();
    }
}
