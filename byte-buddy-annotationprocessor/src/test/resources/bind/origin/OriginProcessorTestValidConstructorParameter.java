package net.bytebuddy.annotationprocessor.advice;


import net.bytebuddy.implementation.bind.annotation.Origin;

import java.lang.reflect.Constructor;


public class OriginProcessorTestValidConstructorParameter {

    public static void test(@Origin Constructor origin) {

    }

}
