package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Executable;


public class OriginProcessorTestValidExecutableParameter {

    @Advice.OnMethodEnter
    public static void test(@Advice.Origin Executable origin) {

    }

}
