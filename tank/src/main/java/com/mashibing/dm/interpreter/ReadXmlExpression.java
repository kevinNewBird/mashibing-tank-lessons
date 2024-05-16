package com.mashibing.dm.interpreter;


/**
 * 用于处理自定义xml取值表达式的接口
 */
public abstract class ReadXmlExpression {

    public abstract String[] interpret(Context c);
}
