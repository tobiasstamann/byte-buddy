package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsvalidator.Validator;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.Unused} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>None</item>
 * </ul>
 */
public class UnusedProcessor extends AbstractByteBuddyAnnotationProcessor {


    private final static Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.Unused.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {


        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.Unused.class)) {
            Element enclosingElement = element.getEnclosingElement();
            if (ElementUtils.CheckKindOfElement.isMethod(enclosingElement)) {

                if (Validator.ANNOTATION_VALIDATOR.getValidator().hasNoneOf(enclosingElement, Advice.OnMethodEnter.class, Advice.OnMethodExit.class)) {
                    getMessager().warning(element, Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getMessage(), "Advice." + Advice.Unused.class.getSimpleName());
                }

            }

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

