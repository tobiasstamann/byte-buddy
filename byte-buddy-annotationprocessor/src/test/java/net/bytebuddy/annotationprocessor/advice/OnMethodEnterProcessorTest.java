package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorIntegrationTest;
import de.holisticon.annotationprocessortoolkit.testhelper.integrationtest.AnnotationProcessorIntegrationTestConfiguration;
import de.holisticon.annotationprocessortoolkit.testhelper.integrationtest.AnnotationProcessorIntegrationTestConfigurationBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class OnMethodEnterProcessorTest extends AbstractAnnotationProcessorIntegrationTest<OnMethodEnterProcessor> {

    public OnMethodEnterProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected OnMethodEnterProcessor getAnnotationProcessor() {
        return new OnMethodEnterProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {
                        "Test valid usage",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/onmethodenter/OnMethodEnterProcessorTestValidUsage.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test annotated method not declared as static",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/onmethodenter/OnMethodEnterProcessorTestMethodNotStatic.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.COMMON__METHOD_MUST_BE_STATIC.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test multiple annotated methods in single class",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/onmethodenter/OnMethodEnterProcessorTestAmbiguousAnnotatedMethods.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.COMMON__AMBIGUOUS_ON_MESSAGE_ENTER_OR_EXIT_METHOD.getCode())
                                .finishMessageValidator()
                                .build()
                },
        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }


}
