package net.bytebuddy.annotationprocessor.bind;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.annotationprocessor.advice.Messages;
import net.bytebuddy.implementation.bind.annotation.AllArguments;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.Set;

/**
 * Annotation processor for {@link net.bytebuddy.implementation.bind.annotation.AllArguments} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>The annotated parameter must be an array type (error)
 * <item>The arrays component type should be Object to ensure best compatibility of interceptor (warning)
 * </ul>
 */
public class AllArgumentsProcessor extends AbstractByteBuddyAnnotationProcessor {

    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(AllArguments.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(AllArguments.class)) {

            // check if annotated parameter is an array type
            if (ElementUtils.CheckKindOfElement.isParameter(element)) {

                VariableElement parameterElement = ElementUtils.CastElement.castToVariableElement(element);

                if (!getTypeUtils().checkTypeKind().isArray(parameterElement.asType())) {

                    getMessager().error(parameterElement, Messages.ALL_ARGUMENTS__ANNOTATED_PARAMETER_MUST_BE_ARRAY.getMessage());

                } else if (!getTypeUtils().isArrayOfType(parameterElement.asType(), Object.class)) {

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
