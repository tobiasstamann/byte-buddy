package net.bytebuddy.annotationprocessor.bind;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorIntegrationTest;
import de.holisticon.annotationprocessortoolkit.testhelper.integrationtest.AnnotationProcessorIntegrationTestConfiguration;
import de.holisticon.annotationprocessortoolkit.testhelper.integrationtest.AnnotationProcessorIntegrationTestConfigurationBuilder;
import net.bytebuddy.annotationprocessor.advice.Messages;
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
                                .setSourceFileToCompile("bind/origin/OriginProcessorTestValidStringParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on Class parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/origin/OriginProcessorTestValidClassParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on Method parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/origin/OriginProcessorTestValidMethodParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on Constructor parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/origin/OriginProcessorTestValidConstructorParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on Executable parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/origin/OriginProcessorTestValidExecutableParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test valid usage on int parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/origin/OriginProcessorTestValidIntParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test invalid usage on Boolean parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/origin/OriginProcessorTestInvalidParameterType.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.ORIGIN__INVALID_TYPE.getCode())
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
