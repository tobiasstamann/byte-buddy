package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class StubValueProcessorTestValidObjectParameter {

    @Advice.OnMethodExit
    public static void test(@Advice.StubValue Object stubValue) {

    }

}
