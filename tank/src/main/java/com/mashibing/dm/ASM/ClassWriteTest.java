package com.mashibing.dm.ASM;

import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/22 17:09
 * @version: 1.0
 ***********************/
public class ClassWriteTest {

    public static void main(String[] args) {
        //整个class字节码在内存里生成
        ClassWriter cw = new ClassWriter(0);
        // 1.ASM生成的接口定义
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object", null);
        // 2.ASM生成接口的常量
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I"
                , null, -1).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I"
                , null, 0).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I"
                , null, 1).visitEnd();
        // 3.ASM生成抽象方法
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo"
                , "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        MyClassLoader mc = new MyClassLoader();
        Class c = mc.defineClass("pkg.Comparable", b);
        System.out.println(c.getMethods()[0].getName());
        System.out.println(c.getFields()[2].getName());


    }
}
