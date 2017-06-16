package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OnMethodExitProcessorTestMethodNotStatic {

    @Advice.OnMethodExit
    public void test() {

    }

}
