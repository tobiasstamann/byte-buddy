package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;


/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.FieldValue} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>None
 * </ul>
 */
public class FieldValueProcessor extends AbstractByteBuddyAnnotationProcessor {

    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.FieldValue.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.FieldValue.class)) {

            // check if parent is either annotated Advice.OnMethodEnter or Advice.OnMethodExit
            this.checkIfEnclosingMethodIsAnnotatatedWithNoneOf(element, Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD, Advice.FieldValue.class, Advice.OnMethodEnter.class, Advice.OnMethodExit.class);


        }

        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_ANNOTATION_TYPES;
    }


}
