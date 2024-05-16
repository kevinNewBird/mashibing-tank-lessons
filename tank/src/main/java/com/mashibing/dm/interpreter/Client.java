package com.mashibing.dm.interpreter;

public class Client {

    public static void main(String[] args) throws Exception {
//        extractMultiEleValue();
        extractMultiAtrrs();
    }

    private static void extractMultiEleValue() throws Exception {
        // 准备上下文
        Context c = new Context("D:\\work\\mashibing-lessons\\mashibing-tank-lessons\\tank\\src\\main\\resources\\InterpreterTest.xml");

        // 想要获取多个d元素的值，也就是如下表达式的值：“root/a/b/d$”
        // 首先要构建解释器的抽象语法树
        ElementExpression root = new ElementExpression("root");
        ElementExpression aEle = new ElementExpression("a");
        ElementExpression bEle = new ElementExpression("b");
        ElementsTerminalExpression dEle = new ElementsTerminalExpression("d");

        // 组合起来
        root.addEle(aEle);
        aEle.addEle(bEle);
        bEle.addEle(dEle);

        // 调用
        String[] ss = root.interpret(c);
        for (String s : ss) {
            System.out.println("d的值是=" + s);
        }

    }


    private static void extractMultiAtrrs() throws Exception {
        // 准备上下文
        Context c = new Context("D:\\work\\mashibing-lessons\\mashibing-tank-lessons\\tank\\src\\main\\resources\\InterpreterTest.xml");

        // 想要获取多个d元素的id属性值，也就是如下表达式的值：“root/a/b/d$.id$”
        // 首先要构建解释器的抽象语法树
        ElementExpression root = new ElementExpression("root");
        ElementExpression aEle = new ElementExpression("a");
        ElementExpression bEle = new ElementExpression("b");
        ElementsExpression dEle = new ElementsExpression("d");
        PropertysTerminalExpression prop = new PropertysTerminalExpression("id");


        // 组合起来
        root.addEle(aEle);
        aEle.addEle(bEle);
        bEle.addEle(dEle);
        dEle.addEle(prop);

        // 调用
        String[] ss = root.interpret(c);
        for (String s : ss) {
            System.out.println("d的值是=" + s);
        }
    }


}
