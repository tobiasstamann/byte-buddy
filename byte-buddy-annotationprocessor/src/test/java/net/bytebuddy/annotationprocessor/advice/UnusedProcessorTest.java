package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * Test for {@link UnusedProcessorTest}.
 */
@RunWith(Parameterized.class)
public class UnusedProcessorTest extends AbstractAnnotationProcessorTest<UnusedProcessor> {

    public UnusedProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected UnusedProcessor getAnnotationProcessor() {
        return new UnusedProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage", "advice/unused/UnusedProcessorTestValidUsage.java", new String[]{}, new String[]{}},
                {"Test missing OnMethodEnter or OnMethodExit annotation on enclosing method", "advice/unused/UnusedProcessorTestMissingOnMethodEnterOrExit.java", new String[]{}, new String[]{Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode()}},

        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }

}
