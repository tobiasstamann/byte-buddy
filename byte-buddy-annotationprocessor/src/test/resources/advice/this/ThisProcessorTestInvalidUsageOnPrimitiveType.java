package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ReturnProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class ThisProcessorTestInvalidUsageOnPrimitiveType {

    @Advice.OnMethodExit
    public static void test1(@Advice.This int x) {

    }

}
