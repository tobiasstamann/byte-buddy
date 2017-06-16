package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class StubValueProcessorTestInvalidParameterType {

    @Advice.OnMethodExit
    public static void test(@Advice.StubValue Boolean origin) {

    }

}
