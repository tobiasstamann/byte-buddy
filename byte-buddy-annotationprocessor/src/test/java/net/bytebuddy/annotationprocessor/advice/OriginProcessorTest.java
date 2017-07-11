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
 * Test for {@link OriginProcessor}.
 */
@RunWith(Parameterized.class)
public class OriginProcessorTest extends AbstractAnnotationProcessorIntegrationTest<OriginProcessor> {

    public OriginProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected OriginProcessor getAnnotationProcessor() {
        return new OriginProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {
                        "Test valid usage on String parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/origin/OriginProcessorTestValidStringParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on Class parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/origin/OriginProcessorTestValidClassParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on Method parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/origin/OriginProcessorTestValidMethodParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on Constructor parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/origin/OriginProcessorTestValidConstructorParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on Executable parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/origin/OriginProcessorTestValidExecutableParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test invalid usage on Boolean parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/origin/OriginProcessorTestInvalidParameterType.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.ORIGIN__INVALID_TYPE.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test missing OnMethodEnter and OnMethodExit annotation on enclosing method",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/origin/OriginProcessorTestMissingOnMethodEnterOrExit.java")
                                .compilationShouldSucceed()
                                .addMessageValidator()
                                .setWarningChecks(Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode())
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
