package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class ThrownProcessorTestValidThrowableParameter {

    @Advice.OnMethodExit
    public static void test(@Advice.Thrown Throwable throwable) {

    }

}
