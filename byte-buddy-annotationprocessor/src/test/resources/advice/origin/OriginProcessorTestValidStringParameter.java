package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OriginProcessorTestValidStringParameter {

    @Advice.OnMethodEnter
    public static void test(@Advice.Origin String origin) {

    }

}
