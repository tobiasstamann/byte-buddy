package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OnMethodExitProcessorTestValidUsage {

    @Advice.OnMethodExit
    public static void test() {

    }

}
