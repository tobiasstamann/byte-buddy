package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * Test for {@link OriginProcessor}.
 */
@RunWith(Parameterized.class)
public class OriginProcessorTest extends AbstractAnnotationProcessorTest<OriginProcessor> {

    public OriginProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Override
    protected OriginProcessor getAnnotationProcessor() {
        return new OriginProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage on String parameter", "advice/origin/OriginProcessorTestValidStringParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on Class parameter", "advice/origin/OriginProcessorTestValidClassParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on Method parameter", "advice/origin/OriginProcessorTestValidMethodParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on Constructor parameter", "advice/origin/OriginProcessorTestValidConstructorParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on Executable parameter", "advice/origin/OriginProcessorTestValidExecutableParameter.java", new String[]{}, new String[]{}},
                {"Test invalid usage on Boolean parameter", "advice/origin/OriginProcessorTestInvalidParameterType.java", new String[]{Messages.ORIGIN__INVALID_TYPE.getCode()}, new String[]{}},
                {"Test missing OnMethodEnter and OnMethodExit annotation on enclosing method", "advice/origin/OriginProcessorTestMissingOnMethodEnterOrExit.java", new String[]{}, new String[]{Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode()}},

        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }

}
