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
 * Test for {@link StubValueProcessor}.
 */
@RunWith(Parameterized.class)
public class StubValueProcessorTest extends AbstractAnnotationProcessorIntegrationTest<StubValueProcessor> {


    public StubValueProcessorTest(String description, AnnotationProcessorIntegrationTestConfiguration configuration) {
        super(configuration);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected StubValueProcessor getAnnotationProcessor() {
        return new StubValueProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {
                        "Test valid usage on Object parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/stubvalue/StubValueProcessorTestValidObjectParameter.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test invalid usage on Boolean parameter",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/stubvalue/StubValueProcessorTestInvalidParameterType.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.STUBVALUE__INCOMPATIBLE_TYPE.getCode())
                                .finishMessageValidator()
                                .build()
                },
                {
                        "Test missing OnMethodExit annotation on enclosing method",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("advice/stubvalue/StubValueProcessorTestMissingOnMethodExit.java")
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