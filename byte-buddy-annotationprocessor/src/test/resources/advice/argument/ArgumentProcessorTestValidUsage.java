package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking valid usage of {@link ArgumentProcessor}.
 */
public class ArgumentProcessorTestValidUsage {

    @Advice.OnMethodEnter
    public static void enterTest(@Advice.Argument(value = 1) Object x) {

    }

    @Advice.OnMethodExit
    public static void exitTest(@Advice.Argument(value = 1) Object x) {

    }

}
