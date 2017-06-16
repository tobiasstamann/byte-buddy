package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Constructor;


public class OriginProcessorTestValidConstructorParameter {

    @Advice.OnMethodEnter
    public static void test(@Advice.Origin Constructor origin) {

    }

}
