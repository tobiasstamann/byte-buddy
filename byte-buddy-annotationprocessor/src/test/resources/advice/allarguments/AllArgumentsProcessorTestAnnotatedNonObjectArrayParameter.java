package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link AllArgumentsProcessor} related to missing {@link Advice.OnMethodEnter} or {@link Advice.OnMethodExit}  annotation
 */
public class AllArgumentsProcessorTestAnnotatedNonObjectArrayParameter {

    @Advice.OnMethodEnter
    public static void test(@Advice.AllArguments() String[] x) {

    }

}
