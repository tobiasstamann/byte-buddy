package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ArgumentProcessor} related to missing {@link Advice.OnMethodEnter} or {@link Advice.OnMethodExit}  annotation
 */
public class ArgumentProcessorTestMissingOnMethodEnterOrExit {

    public static void test(@Advice.Argument(value = 1) Object x) {

    }

}
