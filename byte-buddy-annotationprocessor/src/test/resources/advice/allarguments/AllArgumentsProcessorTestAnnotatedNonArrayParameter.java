package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.annotationprocessor.bind.*;
import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link net.bytebuddy.annotationprocessor.bind.AllArgumentsProcessor} related to annotated non array parameter.
 */
public class AllArgumentsProcessorTestAnnotatedNonArrayParameter {

    @Advice.OnMethodEnter
    public static void test(@Advice.AllArguments() Object x) {

    }

}
