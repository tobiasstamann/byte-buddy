package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.implementation.bind.annotation.AllArguments;

/**
 * Test class for checking compile error for {@link AllArgumentsProcessor} related to missing {@link Advice.OnMethodEnter} or {@link Advice.OnMethodExit}  annotation
 */
public class AllArgumentsProcessorTestValidUsage {

    public static void enterTest(@AllArguments() Object[] x) {

    }

}
