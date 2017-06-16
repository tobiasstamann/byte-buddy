package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OnMethodEnterProcessorTestAmbiguousAnnotatedMethods {

    @Advice.OnMethodEnter
    public static void test() {

    }

    @Advice.OnMethodEnter
    public static void test2() {

    }

}