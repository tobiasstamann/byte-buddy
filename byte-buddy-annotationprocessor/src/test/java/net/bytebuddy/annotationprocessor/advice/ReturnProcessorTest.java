package net.bytebuddy.annotationprocessor.advice;


import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ReturnProcessorTest extends AbstractAnnotationProcessorTest<ReturnProcessor> {

    public ReturnProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected ReturnProcessor getAnnotationProcessor() {
        return new ReturnProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage", "advice/return/ReturnProcessorTestValidUsage.java", new String[]{}, new String[]{}},
                {"Test missing OnMethodExit annotation on method", "advice/return/ReturnProcessorTestMissingOnMethodExit.java", new String[]{}, new String[]{Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode()}},
        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }


}




