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
 * Unit tests for {@AllArgumentsProcessor}.
 */

@RunWith(Parameterized.class)
public class AllArgumentsProcessorTest extends AbstractAnnotationProcessorIntegrationTest<AllArgumentsProcessor> {


    public AllArgumentsProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
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
                                .setSourceFileToCompile("advice/allarguments/AllArgumentsProcessorTestValidUsage.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test missing OnMethodEnter and OnMethodExit annotation on method",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/allarguments/AllArgumentsProcessorTestMissingOnMethodEnterOrExit.java")
                                .compilationShouldSucceed()
                                .addMessageValidator()
                                .setWarningChecks(Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test missing annotated parameter is non array type",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/allarguments/AllArgumentsProcessorTestAnnotatedNonArrayParameter.java")
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
                                .setSourceFileToCompile("advice/allarguments/AllArgumentsProcessorTestAnnotatedNonObjectArrayParameter.java")
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