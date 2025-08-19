package com.mashibing.dm.proxy.demo2.pattern.base;


public interface OrderApi {
    /**
     * 获取订单订购的产品名称
     *
     * @return
     */
    String getProductName();

    /**
     * 设置订单订购的产品名称
     *
     * @param productName：订单订购的产品名称
     * @param operUser：操作人员
     */
    void setProductName(String productName, String operUser);

    /**
     * 获取订单订购数量
     *
     * @return
     */
    int getOrderNum();

    /**
     * 设置订单订购数量
     *
     * @param orderNum
     */
    void setOrderNum(int orderNum, String operUser);

    /**
     * 获取创建订单人员
     *
     * @return
     */
    String getOrderUser();

    /**
     * 设置创建订单的人员
     *
     * @param orderUser：创建订单的人员
     * @param operUser：操作人员
     */
    void setOrderUser(String orderUser, String operUser);
}
