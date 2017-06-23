package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.implementation.bind.annotation.AllArguments;

/**
 * Test class for checking compile error for {@link net.bytebuddy.annotationprocessor.bind.AllArgumentsProcessor} related to annotated non array parameter.
 */
public class AllArgumentsProcessorTestAnnotatedNonObjectArrayParameter {

    public static void test(@AllArguments() String[] x) {

    }

}
