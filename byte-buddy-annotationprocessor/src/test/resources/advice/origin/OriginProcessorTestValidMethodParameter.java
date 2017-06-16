package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;


public class OriginProcessorTestValidMethodParameter {

    @Advice.OnMethodEnter
    public static void test(@Advice.Origin Method origin) {

    }

}
