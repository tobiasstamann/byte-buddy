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
 * Annotation processor for {@link net.bytebuddy.asm.Advice.Return} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>should be used on parameters in a method annotated with {@link net.bytebuddy.asm.Advice.OnMethodExit}</item>
 * </ul>
 */

public class ReturnProcessor extends AbstractByteBuddyAnnotationProcessor {


    private final static Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.Return.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.Return.class)) {

            // check if parent is either annotated  Advice.OnMethodExit
            Element enclosingElement = element.getEnclosingElement();
            if (ElementUtils.CheckKindOfElement.isMethod(enclosingElement)) {

                // check if parent method is annotated with Advice.OnMethodExit
                if (Validator.ANNOTATION_VALIDATOR.getValidator().hasNoneOf(enclosingElement, Advice.OnMethodExit.class)) {
                    getMessager().warning(element, Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getMessage(), "Advice." + Advice.Return.class.getSimpleName());
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
