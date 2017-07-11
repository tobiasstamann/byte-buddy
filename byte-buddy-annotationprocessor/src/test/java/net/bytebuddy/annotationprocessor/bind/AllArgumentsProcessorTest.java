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
 * Unit tests for {@AllArgumentsProcessor}.
 */

@RunWith(Parameterized.class)
public class AllArgumentsProcessorTest extends AbstractAnnotationProcessorIntegrationTest<AllArgumentsProcessor> {

    public AllArgumentsProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Override
    protected AllArgumentsProcessor getAnnotationProcessor() {
        return new AllArgumentsProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {
                        "Test valid usage",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/allarguments/AllArgumentsProcessorTestValidUsage.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test missing annotated parameter is non array type",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/allarguments/AllArgumentsProcessorTestAnnotatedNonArrayParameter.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.ALL_ARGUMENTS__ANNOTATED_PARAMETER_MUST_BE_ARRAY.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test missing annotated parameter is array but not object array",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/allarguments/AllArgumentsProcessorTestAnnotatedNonObjectArrayParameter.java")
                                .compilationShouldSucceed()
                                .addMessageValidator()
                                .setWarningChecks(Messages.ALL_ARGUMENTS__ANNOTATED_PARAMETER_SHOULD_BE_OBJECT_ARRAY.getCode())
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