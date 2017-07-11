package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.Set;

/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.Thrown} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>the parameter type should be assignable to Throwable
 * </ul>
 */

public class ThrownProcessor extends AbstractByteBuddyAnnotationProcessor {

    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.Thrown.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.Thrown.class)) {

            // check if parent is annotated Advice.OnMethodExit
            this.checkIfEnclosingMethodIsAnnotatatedWithNoneOf(element, Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD, Advice.Thrown.class, Advice.OnMethodExit.class);


            if (ElementUtils.CheckKindOfElement.isParameter(element)) {

                VariableElement parameterElement = ElementUtils.CastElement.castParameter(element);


                // Now check if annotated type is Throwable
                TypeMirror throwableTypeMirror = getTypeUtils().doTypeRetrieval().getTypeMirror(Throwable.class);
                if (!getTypeUtils().doTypeComparison().isErasedTypeEqual(throwableTypeMirror, parameterElement.asType())) {

                    // if not, check if it is assignable to Throwable
                    if (getTypeUtils().doTypeComparison().isAssignableTo(parameterElement.asType(), throwableTypeMirror)) {
                        this.getMessager().warning(parameterElement, Messages.THROWN__SHOULD_BE_THROWABLE.getMessage());
                    } else {
                        // otherwise report error
                        this.getMessager().error(parameterElement, Messages.THROWN__SHOULD_AT_LEAST_EXTEND_THROWABLE.getMessage());
                    }
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

