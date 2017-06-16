package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OriginProcessorTestValidClassParameter {

    @Advice.OnMethodEnter
    public static void test(@Advice.Origin Class origin) {

    }

}
