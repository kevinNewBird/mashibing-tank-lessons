package com.mashibing.dm.ASM;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;

/***********************
 * @Description: TODO 类描述<BR>
 * @author: zhao.song
 * @since: 2021/3/22 0:49
 * @version: 1.0
 ***********************/
public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(ASM4);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("   " + name + "()");
        return null;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("  " + name);
        return null;
    }

    @Override
    public void visitEnd() {
        System.out.println(" }");
    }

    public static void main(String[] args) throws IOException {
        ClassPrinter printer = new ClassPrinter();
        ClassReader reader = new ClassReader("java.lang.Runnable");
        reader.accept(printer, 0);

    }
}
