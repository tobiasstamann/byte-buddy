package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ThrownProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class ThrownProcessorTestMissingOnMethodExit {

    public static void test(@Advice.Thrown Throwable x) {

    }

}
