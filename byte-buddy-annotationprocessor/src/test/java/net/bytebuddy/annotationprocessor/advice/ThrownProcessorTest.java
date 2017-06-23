package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
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
public class ThrownProcessorTest extends AbstractAnnotationProcessorTest<ThrownProcessor> {

    public ThrownProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
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
                {"Test valid usage on Throwable parameter", "advice/thrown/ThrownProcessorTestValidThrowableParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on parameter assignable to Throwable", "advice/thrown/ThrownProcessorTestValidAssignableToThrowableParameter.java", new String[]{}, new String[]{Messages.THROWN__SHOULD_BE_THROWABLE.getCode()}},
                {"Test invalid usage on Boolean parameter", "advice/thrown/ThrownProcessorTestInvalidType.java", new String[]{Messages.THROWN__SHOULD_AT_LEAST_EXTEND_THROWABLE.getCode()}, new String[]{}},
                {"Test missing OnMethodExit annotation on enclosing method", "advice/thrown/ThrownProcessorTestMissingOnMethodExit.java", new String[]{}, new String[]{Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode()}},

        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }

}
