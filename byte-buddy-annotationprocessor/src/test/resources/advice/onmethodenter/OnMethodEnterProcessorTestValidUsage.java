package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OnMethodEnterProcessorTestValidUsage {

    @Advice.OnMethodEnter
    public static void test() {

    }

}
