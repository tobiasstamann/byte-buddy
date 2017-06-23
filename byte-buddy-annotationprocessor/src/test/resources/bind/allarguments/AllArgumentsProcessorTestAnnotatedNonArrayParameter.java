package net.bytebuddy.annotationprocessor.bind;


import net.bytebuddy.implementation.bind.annotation.AllArguments;

/**
 * Test class for checking compile error for {@link AllArgumentsProcessor} related to annotated non array parameter.
 */
public class AllArgumentsProcessorTestAnnotatedNonArrayParameter {

    public static void test(@AllArguments() Object x) {

    }

}
