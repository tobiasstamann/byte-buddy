package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link StubValueProcessor} related to missing  {@link Advice.OnMethodExit}  annotation
 */
public class StubValueProcessorTestMissingOnMethodExit {

    public static void test(@Advice.StubValue Object x) {

    }

}
