package net.bytebuddy.annotationprocessor.advice;


import net.bytebuddy.implementation.bind.annotation.Argument;

/**
 * Test class for checking valid usage of {@link ArgumentProcessor}.
 */
public class ArgumentProcessorTestValidUsage {

    public static void enterTest(@Argument(value = 1) Object x) {

    }

}
