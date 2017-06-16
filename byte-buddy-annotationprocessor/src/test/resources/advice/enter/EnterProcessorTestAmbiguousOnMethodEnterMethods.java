package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ReturnProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class EnterProcessorTestAmbiguousOnMethodEnterMethods {

    @Advice.OnMethodEnter
    public static String enter1() {
        return "TEST";
    }

    @Advice.OnMethodEnter
    public static String enter2() {
        return "TEST";
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.Enter String x) {

    }

}
