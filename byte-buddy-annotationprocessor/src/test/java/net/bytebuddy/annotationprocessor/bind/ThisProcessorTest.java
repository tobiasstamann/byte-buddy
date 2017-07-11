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
 * Test for {@link net.bytebuddy.annotationprocessor.advice.ThisProcessor}.
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
                                .setSourceFileToCompile("bind/this/ThisProcessorTestValidUsage.java")
                                .compilationShouldSucceed()
                                .build()
                },
                {
                        "Test invalid usage on primitivetype",
                        AnnotationProcessorIntegrationTestConfigurationBuilder
                                .createTestConfig()
                                .setSourceFileToCompile("bind/this/ThisProcessorTestInvalidUsageOnPrimitiveType.java")
                                .compilationShouldFail()
                                .addMessageValidator()
                                .setErrorChecks(Messages.COMMON__PARAMETER_MUST_NOT_HAVE_PRIMITIVE_TYPE.getCode())
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
