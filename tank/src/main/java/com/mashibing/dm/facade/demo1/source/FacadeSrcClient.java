package com.mashibing.dm.facade.demo1.source;

import com.mashibing.dm.facade.demo1.base.generator.impl.ControllerGenerator;
import com.mashibing.dm.facade.demo1.base.generator.impl.DaoGenerator;
import com.mashibing.dm.facade.demo1.base.generator.impl.ServiceGenerator;

/**
 * 没有使用设计模式的案例测试用例
 */
public class FacadeSrcClient {


    public static void main(String[] args) {
        new ControllerGenerator().generate();
        new ServiceGenerator().generate();
        new DaoGenerator().generate();

        /**
         * 正在生成表现层代码文件...
         * 正在生成逻辑层代码文件...
         * 正在生成数据层代码文件...
         */
    }
}
