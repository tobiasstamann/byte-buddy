package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for {@ArgumentProcessor}.
 */

@RunWith(Parameterized.class)

public class ArgumentProcessorTest extends AbstractAnnotationProcessorTest<ArgumentProcessor> {


    public ArgumentProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Override
    protected ArgumentProcessor getAnnotationProcessor() {
        return new ArgumentProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage", "advice/argument/ArgumentProcessorTestValidUsage.java", new String[]{}, new String[]{}},
                {"Test missing OnMethodEnter and OnMethodExit annotation on method", "advice/argument/ArgumentProcessorTestMissingOnMethodEnterOrExit.java", new String[]{}, new String[]{Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode()}},
        });

    }


    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }


}
