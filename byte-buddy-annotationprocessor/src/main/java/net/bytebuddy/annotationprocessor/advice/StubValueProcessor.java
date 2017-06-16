package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsvalidator.Validator;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.Set;

/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.StubValue} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>The annotated parameter must be a of type Object
 * <item>should be used on parameters in a method annotated with {@link net.bytebuddy.asm.Advice.OnMethodExit}
 * </ul>
 */
public class StubValueProcessor extends AbstractByteBuddyAnnotationProcessor {

    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.StubValue.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.StubValue.class)) {

            // check if parent is either annotated  Advice.OnMethodExit
            Element enclosingElement = element.getEnclosingElement();
            if (ElementUtils.CheckKindOfElement.isMethod(enclosingElement)) {

                // check if parent method is annotated with Advice.OnMethodExit
                if (Validator.ANNOTATION_VALIDATOR.getValidator().hasNoneOf(enclosingElement, Advice.OnMethodExit.class)) {
                    getMessager().warning(element, Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getMessage(), "Advice." + Advice.StubValue.class.getSimpleName());
                }

            }

            // check if parent is either annotated Advice.OnMethodExit
            VariableElement variableElement = ElementUtils.CastElement.castParameter(element);

            // check for annotated parameter type
            if (Validator.getTypeValidator(frameworkToolWrapper)
                    .hasNoneOf(variableElement, Object.class)) {
                getMessager().error(variableElement, Messages.STUBVALUE__INCOMPATIBLE_TYPE.getMessage());
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

