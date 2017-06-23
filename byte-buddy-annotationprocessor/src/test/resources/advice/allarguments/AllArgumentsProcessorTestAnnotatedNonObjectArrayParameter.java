package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link net.bytebuddy.annotationprocessor.bind.AllArgumentsProcessor} related to annotated non Object array parameter.
 */
public class AllArgumentsProcessorTestAnnotatedNonObjectArrayParameter {

    @Advice.OnMethodEnter
    public static void test(@Advice.AllArguments() String[] x) {

    }

}
