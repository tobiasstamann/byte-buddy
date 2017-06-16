package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OnMethodExitProcessorTestAmbiguousAnnotatedMethods {

    @Advice.OnMethodExit
    public static void test() {

    }

    @Advice.OnMethodExit
    public static void test2() {

    }

}