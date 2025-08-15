package com.mashibing.dm.facade.demo1.pattern;

import com.mashibing.dm.facade.demo1.base.generator.impl.ControllerGenerator;
import com.mashibing.dm.facade.demo1.base.generator.impl.DaoGenerator;
import com.mashibing.dm.facade.demo1.base.generator.impl.ServiceGenerator;

/**
 * description: 外观模式
 */
public class FacadeModel {

    public void run(){
        /**
         * 要使用外观模式重写前面的示例，其实非常简单，只须添加一个Facade的对象，然后在里面实现客户端需要的功能就可以了。
         */
        new ControllerGenerator().generate();
        new ServiceGenerator().generate();
        new DaoGenerator().generate();
    }
}
