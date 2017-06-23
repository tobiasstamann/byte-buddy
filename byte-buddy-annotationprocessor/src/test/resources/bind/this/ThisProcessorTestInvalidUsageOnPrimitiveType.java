package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.implementation.bind.annotation.This;

public class ThisProcessorTestInvalidUsageOnPrimitiveType {

    public static void test1(@This int x) {

    }

}
