package net.bytebuddy.annotationprocessor.advice;


import net.bytebuddy.implementation.bind.annotation.Origin;

public class OriginProcessorTestInvalidParameterType {

    public static void test(@Origin Boolean origin) {

    }

}
