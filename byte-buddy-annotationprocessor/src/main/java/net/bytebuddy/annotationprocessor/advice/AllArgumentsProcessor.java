package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.Set;

/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.AllArguments} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>The annotated parameter must be an array type
 * <item>The annotation should be used on parameters of methods annotated either with {@link Advice.OnMethodEnter} or {@link Advice.OnMethodExit}
 * </ul>
 */
public class AllArgumentsProcessor extends AbstractByteBuddyAnnotationProcessor {


    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.AllArguments.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.AllArguments.class)) {

            // check if parent is either annotated Advice.OnMethodEnter or Advice.OnMethodExit
            this.checkIfEnclosingMethodIsAnnotatatedWithNoneOf(element, Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD, Advice.AllArguments.class, Advice.OnMethodEnter.class, Advice.OnMethodExit.class);

            // check if annotated parameter is an array type
            if (ElementUtils.CheckKindOfElement.isParameter(element)) {

                VariableElement parameterElement = ElementUtils.CastElement.castToVariableElement(element);

                if (!getTypeUtils().doCheckTypeKind().isArray(parameterElement.asType())) {

                    getMessager().error(parameterElement, Messages.ALL_ARGUMENTS__ANNOTATED_PARAMETER_MUST_BE_ARRAY.getMessage());

                } else if (!getTypeUtils().doArrays().isArrayOfType(parameterElement.asType(), Object.class)) {

                    // check if annotated parameter is an object array for best compatibility
                    getMessager().warning(element, Messages.ALL_ARGUMENTS__ANNOTATED_PARAMETER_SHOULD_BE_OBJECT_ARRAY.getMessage());

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
