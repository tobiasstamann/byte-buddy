package net.bytebuddy.annotationprocessor.bind;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
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
                {"Test valid usage", "bind/this/ThisProcessorTestValidUsage.java", new String[]{}, new String[]{}},
                {"Test invalid usage on primitivetype", "bind/this/ThisProcessorTestInvalidUsageOnPrimitiveType.java", new String[]{Messages.COMMON__PARAMETER_MUST_NOT_HAVE_PRIMITIVE_TYPE.getCode()}, new String[]{}},

        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }

}
