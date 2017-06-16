package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ReturnProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class UnusedProcessorTestValidUsage {

    @Advice.OnMethodExit
    public static void test1(@Advice.Unused Object x) {

    }

    @Advice.OnMethodEnter
    public static void test2(@Advice.Unused Object x) {

    }

}
