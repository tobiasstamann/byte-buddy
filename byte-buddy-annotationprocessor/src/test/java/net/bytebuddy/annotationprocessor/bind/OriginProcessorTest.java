package net.bytebuddy.annotationprocessor.bind;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import net.bytebuddy.annotationprocessor.advice.Messages;
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
                {"Test valid usage on String parameter", "bind/origin/OriginProcessorTestValidStringParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on Class parameter", "bind/origin/OriginProcessorTestValidClassParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on Method parameter", "bind/origin/OriginProcessorTestValidMethodParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on Constructor parameter", "bind/origin/OriginProcessorTestValidConstructorParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on Executable parameter", "bind/origin/OriginProcessorTestValidExecutableParameter.java", new String[]{}, new String[]{}},
                {"Test valid usage on int parameter", "bind/origin/OriginProcessorTestValidIntParameter.java", new String[]{}, new String[]{}},
                {"Test invalid usage on Boolean parameter", "bind/origin/OriginProcessorTestInvalidParameterType.java", new String[]{Messages.ORIGIN__INVALID_TYPE.getCode()}, new String[]{}},

        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }

}
