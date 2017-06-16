package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ReturnProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class ReturnProcessorTestInvalidType {

    @Advice.OnMethodExit
    public static void test(@Advice.Return String x) {

    }

}
