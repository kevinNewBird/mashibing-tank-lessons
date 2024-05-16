package com.mashibing.dm.facade;

/**
 * 代码生成器门面
 */
public class CodeGeneratorFacade {

    public void process() {
        new PresentationModelGenerator().process();
        new BusinessModelGenerator().process();
        new DAOModelGenerator().process();
    }
}
