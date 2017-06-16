package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


/**
 * Test for {@link StubValueProcessor}.
 */
@RunWith(Parameterized.class)
public class StubValueProcessorTest extends AbstractAnnotationProcessorTest<StubValueProcessor> {


    public StubValueProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Override
    protected StubValueProcessor getAnnotationProcessor() {
        return new StubValueProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage on Object parameter", "advice/stubvalue/StubValueProcessorTestValidObjectParameter.java", new String[]{}, new String[]{}},
                {"Test invalid usage on Boolean parameter", "advice/stubvalue/StubValueProcessorTestInvalidParameterType.java", new String[]{Messages.STUBVALUE__INCOMPATIBLE_TYPE.getCode()}, new String[]{}},
                {"Test missing OnMethodExit annotation on enclosing method", "advice/stubvalue/StubValueProcessorTestMissingOnMethodExit.java", new String[]{}, new String[]{Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode()}},

        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }

}