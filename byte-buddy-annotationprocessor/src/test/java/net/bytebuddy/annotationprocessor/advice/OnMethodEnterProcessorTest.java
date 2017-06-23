package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.testhelper.AbstractAnnotationProcessorTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class OnMethodEnterProcessorTest extends AbstractAnnotationProcessorTest<OnMethodEnterProcessor> {

    public OnMethodEnterProcessorTest(String description, String resource, String[] errors, String[] warnings) {
        super(description, resource, errors, warnings);
    }

    @Before
    public void init() {
        Messages.setPrintMessageCodes(true);
    }

    @Override
    protected OnMethodEnterProcessor getAnnotationProcessor() {
        return new OnMethodEnterProcessor();
    }

    @Parameterized.Parameters(name = "{index}: \"{0}\"")
    public static List<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Test valid usage", "advice/onmethodenter/OnMethodEnterProcessorTestValidUsage.java", new String[]{}, new String[]{}},
                {"Test annotated method not declared as static", "advice/onmethodenter/OnMethodEnterProcessorTestMethodNotStatic.java", new String[]{Messages.COMMON__METHOD_MUST_BE_STATIC.getCode()}, new String[]{}},
                {"Test multiple annotated methods in single class", "advice/onmethodenter/OnMethodEnterProcessorTestAmbiguousAnnotatedMethods.java", new String[]{Messages.COMMON__AMBIGUOUS_ON_MESSAGE_ENTER_OR_EXIT_METHOD.getCode()}, new String[]{}},
        });

    }

    @Test
    public void testCorrectnessOfAdviceArgumentAnnotation() {
        super.test();
    }


}
