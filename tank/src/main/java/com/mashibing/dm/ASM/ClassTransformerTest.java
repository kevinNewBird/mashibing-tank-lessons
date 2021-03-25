package com.mashibing.dm.ASM;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static org.objectweb.asm.Opcodes.*;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/23 17:24
 * @version: 1.0
 ***********************/
public class ClassTransformerTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //ASM整个流程: Reader->Adapter->Writer
        //其中Adapter就是Visitor
        // 1.读取字节码文件
        ClassReader cr = new ClassReader(
                ClassPrinter.class.getClassLoader().getResourceAsStream("com/mashibing/dm/ASM/Tank.class"));

        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM4,cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                return new MethodVisitor(ASM5, mv) {
                    @Override
                    public void visitCode() {
                        visitMethodInsn(INVOKESTATIC, "com/mashibing/dm/ASM/TimeProxy", "before"
                                , "()V", false);
                        super.visitCode();
                    }
                };
            }
        };

        cr.accept(cv, 0);

        byte[] b2 = cw.toByteArray();

        MyClassLoader mc = new MyClassLoader();
        mc.loadClass("com.mashibing.dm.ASM.TimeProxy");
        Class c2 = mc.defineClass("com.mashibing.dm.ASM.Tank", b2);
        c2.getMethod("move", null).invoke(c2.newInstance(), null);

        String path = System.getProperty("user.dir");
        System.out.println(path);
        File f = new File(path + "/com/mashibing/dm/ASM/");
        f.mkdirs();

        FileOutputStream fos = new FileOutputStream(new File(path + "/com/mashibing/dm/ASM/Tank.class"));
        fos.write(b2);
        fos.flush();
        fos.close();


    }
}
