package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ThrownProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class ThrownProcessorTestInvalidType {

    @Advice.OnMethodExit
    public static void test(@Advice.Thrown String x) {

    }

}
