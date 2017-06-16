package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OriginProcessorTestInvalidParameterType {

    @Advice.OnMethodEnter
    public static void test(@Advice.Origin Boolean origin) {

    }

}
