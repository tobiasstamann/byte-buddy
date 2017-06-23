package net.bytebuddy.annotationprocessor.bind;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import net.bytebuddy.annotationprocessor.advice.Messages;
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
                {"Test valid usage on Object parameter", "bind/stubvalue/StubValueProcessorTestValidObjectParameter.java", new String[]{}, new String[]{}},
                {"Test invalid usage on Boolean parameter", "bind/stubvalue/StubValueProcessorTestInvalidParameterType.java", new String[]{Messages.STUBVALUE__INCOMPATIBLE_TYPE.getCode()}, new String[]{}},

        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }

}