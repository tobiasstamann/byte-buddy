package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;


public class OnMethodEnterProcessorTestMethodNotStatic {

    @Advice.OnMethodEnter
    public void test() {

    }

}
