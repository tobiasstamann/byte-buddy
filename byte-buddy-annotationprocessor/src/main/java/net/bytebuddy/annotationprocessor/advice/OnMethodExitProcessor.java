package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.OnMethodExit} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>annotated method has to be static.
 * </ul>
 */
public class OnMethodExitProcessor extends AbstractByteBuddyAnnotationProcessor {

    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.OnMethodExit.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.OnMethodExit.class)) {

            if (ElementUtils.CheckKindOfElement.isMethod(element)) {
                ExecutableElement executableElement = ElementUtils.CastElement.castMethod(element);

                // check if there's only one method annotated with OnMethodExit
                checkForAmbiguousUsageOfAnnotationsOnMultipleMethods(executableElement, Advice.OnMethodExit.class);

                // check if it is declared as static
                getFluentModifierElementValidator(element).setCustomMessage(Diagnostic.Kind.ERROR, Messages.COMMON__METHOD_MUST_BE_STATIC.getMessage(), Advice.OnMethodExit.class.getSimpleName()).hasModifiers(Modifier.STATIC);


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
