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
public class EnterProcessorTest extends AbstractAnnotationProcessorIntegrationTest<EnterProcessor> {


    public EnterProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected EnterProcessor getAnnotationProcessor() {
        return new EnterProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                        {
                                "Test valid usage",
                                AnnotationProcessorIntegrationTestConfigurationBuilder
                                        .createTestConfig()
                                        .setSourceFileToCompile("advice/enter/EnterProcessorTestValidUsage.java")
                                        .compilationShouldSucceed()
                                        .build()
                        },
                        {
                                "Test missing OnMethodExit annotation on method",
                                AnnotationProcessorIntegrationTestConfigurationBuilder
                                        .createTestConfig()
                                        .setSourceFileToCompile("advice/enter/EnterProcessorTestMissingOnMethodExit.java")
                                        .compilationShouldSucceed()
                                        .addMessageValidator()
                                        .setWarningChecks(Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode())
                                        .finishMessageValidator()
                                        .build()
                        },
                        {
                                "Test missing OnMethodEnter annotation on method",
                                AnnotationProcessorIntegrationTestConfigurationBuilder
                                        .createTestConfig()
                                        .setSourceFileToCompile("advice/enter/EnterProcessorTestMissingOnMethodEnter.java")
                                        .compilationShouldSucceed()
                                        .addMessageValidator()
                                        .setWarningChecks(Messages.ENTER__NO_ON_METHOD_ENTER_METHOD_DETECTED.getCode())
                                        .finishMessageValidator()
                                        .build()
                        },
                        {
                                "Test ambiguous OnMethodEnter annotations on methods",
                                AnnotationProcessorIntegrationTestConfigurationBuilder
                                        .createTestConfig()
                                        .setSourceFileToCompile("advice/enter/EnterProcessorTestAmbiguousOnMethodEnterMethods.java")
                                        .compilationShouldSucceed()
                                        .addMessageValidator()
                                        .setWarningChecks(Messages.ENTER__AMBIGUOUS_ONMETHOD_ENTER.getCode())
                                        .finishMessageValidator().build()
                        },
                        {
                                "Test incompatible enter type",
                                AnnotationProcessorIntegrationTestConfigurationBuilder
                                        .createTestConfig()
                                        .setSourceFileToCompile("advice/enter/EnterProcessorTestIncompatibleTypes.java")
                                        .compilationShouldSucceed()
                                        .addMessageValidator()
                                        .setWarningChecks(Messages.ENTER__INCOMPATIBLE_TYPE.getCode())
                                        .finishMessageValidator()
                                        .build()
                        },
                }

        );

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }


}
