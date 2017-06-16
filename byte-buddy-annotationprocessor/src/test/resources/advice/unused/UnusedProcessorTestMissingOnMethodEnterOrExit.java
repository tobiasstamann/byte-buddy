package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ReturnProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class UnusedProcessorTestMissingOnMethodEnterOrExit {

     public static void test1(@Advice.Unused Object x) {

    }

    public static void test2s(@Advice.Unused Object x) {

    }

}
