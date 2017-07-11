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


/**
 * Test for {@link ThrownProcessor}.
 */
@RunWith(Parameterized.class)
public class ThrownProcessorTest extends AbstractAnnotationProcessorIntegrationTest<ThrownProcessor> {

    public ThrownProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected ThrownProcessor getAnnotationProcessor() {
        return new ThrownProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {
                        "Test valid usage on Throwable parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/thrown/ThrownProcessorTestValidThrowableParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on parameter assignable to Throwable",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/thrown/ThrownProcessorTestValidAssignableToThrowableParameter.java")
                                .compilationShouldSucceed()
                                .addMessageValidator()
                                .setWarningChecks(Messages.THROWN__SHOULD_BE_THROWABLE.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test invalid usage on Boolean parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/thrown/ThrownProcessorTestInvalidType.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.THROWN__SHOULD_AT_LEAST_EXTEND_THROWABLE.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test missing OnMethodExit annotation on enclosing method",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/thrown/ThrownProcessorTestMissingOnMethodExit.java")
                                .compilationShouldSucceed()
                                .addMessageValidator()
                                .setWarningChecks(Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode())
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
