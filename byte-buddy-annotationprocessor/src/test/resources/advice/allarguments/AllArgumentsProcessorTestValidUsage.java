package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link AllArgumentsProcessor} related to missing {@link Advice.OnMethodEnter} or {@link Advice.OnMethodExit}  annotation
 */
public class AllArgumentsProcessorTestValidUsage {

    @Advice.OnMethodEnter
    public static void enterTest(@Advice.AllArguments() Object[] x) {

    }

    @Advice.OnMethodExit
    public static void exitTest(@Advice.AllArguments() Object[] x) {

    }

}
