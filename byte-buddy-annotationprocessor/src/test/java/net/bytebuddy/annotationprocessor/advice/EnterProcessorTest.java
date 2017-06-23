package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class EnterProcessorTest extends AbstractAnnotationProcessorTest {


    public EnterProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected EnterProcessor getAnnotationProcessor() {
        return new EnterProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage", "advice/enter/EnterProcessorTestValidUsage.java", new String[]{}, new String[]{}},
                {"Test missing OnMethodExit annotation on method", "advice/enter/EnterProcessorTestMissingOnMethodExit.java", new String[]{}, new String[]{Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getCode()}},
                {"Test missing OnMethodEnter annotation on method", "advice/enter/EnterProcessorTestMissingOnMethodEnter.java", new String[]{}, new String[]{Messages.ENTER__NO_ON_METHOD_ENTER_METHOD_DETECTED.getCode()}},
                {"Test ambiguous OnMethodEnter annotations on methods", "advice/enter/EnterProcessorTestAmbiguousOnMethodEnterMethods.java", new String[]{}, new String[]{Messages.ENTER__AMBIGUOUS_ONMETHOD_ENTER.getCode()}},
                {"Test incompatible enter type", "advice/enter/EnterProcessorTestIncompatibleTypes.java", new String[]{}, new String[]{Messages.ENTER__INCOMPATIBLE_TYPE.getCode()}},
        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }


}
