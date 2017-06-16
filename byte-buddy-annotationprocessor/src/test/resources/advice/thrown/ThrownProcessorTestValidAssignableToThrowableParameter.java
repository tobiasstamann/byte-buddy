package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class ThrownProcessorTestValidAssignableToThrowableParameter {

    @Advice.OnMethodExit
    public static void test(@Advice.Thrown NullPointerException throwable) {

    }

}
