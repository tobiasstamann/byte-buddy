package net.bytebuddy.annotationprocessor.advice;


import net.bytebuddy.implementation.bind.annotation.StubValue;

public class StubValueProcessorTestInvalidParameterType {

    public static void test(@StubValue Boolean origin) {

    }

}
