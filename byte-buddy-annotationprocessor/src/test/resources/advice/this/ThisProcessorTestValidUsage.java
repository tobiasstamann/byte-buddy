package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ReturnProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class ThisProcessorTestValidUsage {

    @Advice.OnMethodExit
    public static void test1(@Advice.This Object x) {

    }

    @Advice.OnMethodEnter
    public static void test2(@Advice.This Object x) {

    }

}
