package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ReturnProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class FieldValueProcessorTestMissingOnMethodEnterOrExit {

    public static void test1(@Advice.FieldValue("x") Object x) {

    }

    public static void test2s(@Advice.FieldValue("x") Object x) {

    }

}
