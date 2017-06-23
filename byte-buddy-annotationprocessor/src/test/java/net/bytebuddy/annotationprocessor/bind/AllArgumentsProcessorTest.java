package net.bytebuddy.annotationprocessor.bind;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import net.bytebuddy.annotationprocessor.advice.Messages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for {@AllArgumentsProcessor}.
 */

@RunWith(Parameterized.class)
public class AllArgumentsProcessorTest extends AbstractAnnotationProcessorTest<AllArgumentsProcessor> {

    public AllArgumentsProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Override
    protected AllArgumentsProcessor getAnnotationProcessor() {
        return new AllArgumentsProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage", "bind/allarguments/AllArgumentsProcessorTestValidUsage.java", new String[]{}, new String[]{}},
                {"Test missing annotated parameter is non array type", "bind/allarguments/AllArgumentsProcessorTestAnnotatedNonArrayParameter.java", new String[]{Messages.ALL_ARGUMENTS__ANNOTATED_PARAMETER_MUST_BE_ARRAY.getCode()}, new String[]{}},
                {"Test missing annotated parameter is array but not object array", "bind/allarguments/AllArgumentsProcessorTestAnnotatedNonObjectArrayParameter.java", new String[]{}, new String[]{Messages.ALL_ARGUMENTS__ANNOTATED_PARAMETER_SHOULD_BE_OBJECT_ARRAY.getCode()}},
        });

    }


    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }


}