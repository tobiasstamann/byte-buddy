package net.bytebuddy.annotationprocessor.advice;


import net.bytebuddy.implementation.bind.annotation.Origin;

import java.lang.reflect.Executable;


public class OriginProcessorTestValidExecutableParameter {

    public static void test(@Origin Executable origin) {

    }

}
