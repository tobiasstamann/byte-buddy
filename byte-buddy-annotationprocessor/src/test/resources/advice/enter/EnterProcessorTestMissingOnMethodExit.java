package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Test class for checking compile error for {@link ReturnProcessor} related to missing {@link Advice.OnMethodExit}  annotation
 */
public class EnterProcessorTestMissingOnMethodExit {


    @Advice.OnMethodEnter
    public static String enter() {
        return "TEST";
    }


    public static void exit(@Advice.Enter Object x) {

    }
}
