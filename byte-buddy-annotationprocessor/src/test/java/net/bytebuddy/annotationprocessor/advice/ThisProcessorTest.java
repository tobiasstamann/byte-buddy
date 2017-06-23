package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


/**
 * Test for {@link ThisProcessor}.
 */
@RunWith(Parameterized.class)
public class ThisProcessorTest extends AbstractAnnotationProcessorTest<ThisProcessor> {

    public ThisProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Override
    protected ThisProcessor getAnnotationProcessor() {
        return new ThisProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage", "advice/this/ThisProcessorTestValidUsage.java", new String[]{}, new String[]{}},
                {"Test invalid usage on primitivetype", "advice/this/ThisProcessorTestInvalidUsageOnPrimitiveType.java", new String[]{Messages.COMMON__PARAMETER_MUST_NOT_HAVE_PRIMITIVE_TYPE.getCode()}, new String[]{}},
                {"Test missing OnMethodEnter or OnMethodExit annotation on enclosing method", "advice/this/ThisProcessorTestMissingOnMethodEnterOrExit.java", new String[]{}, new String[]{Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode()}},

        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }

}
