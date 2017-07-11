package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorIntegrationTest;
import de.holisticon.annotationprocessortoolkit.testhelper.integrationtest.AnnotationProcessorIntegrationTestConfiguration;
import de.holisticon.annotationprocessortoolkit.testhelper.integrationtest.AnnotationProcessorIntegrationTestConfigurationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


/**
 * Test for {@link ThisProcessor}.
 */
@RunWith(Parameterized.class)
public class ThisProcessorTest extends AbstractAnnotationProcessorIntegrationTest<ThisProcessor> {

    public ThisProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Override
    protected ThisProcessor getAnnotationProcessor() {
        return new ThisProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {
                        "Test valid usage",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/this/ThisProcessorTestValidUsage.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test invalid usage on primitivetype",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/this/ThisProcessorTestInvalidUsageOnPrimitiveType.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.COMMON__PARAMETER_MUST_NOT_HAVE_PRIMITIVE_TYPE.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test missing OnMethodEnter or OnMethodExit annotation on enclosing method",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/this/ThisProcessorTestMissingOnMethodEnterOrExit.java")
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
