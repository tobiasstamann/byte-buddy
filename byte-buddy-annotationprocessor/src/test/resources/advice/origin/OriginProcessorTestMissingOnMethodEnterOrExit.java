package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link OriginProcessor} related to missing {@link Advice.OnMethodEnter} or {@link Advice.OnMethodExit}  annotation
 */
public class OriginProcessorTestMissingOnMethodEnterOrExit {

    public static void test(@Advice.Origin String x) {

    }

}
