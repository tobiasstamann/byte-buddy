package net.bytebuddy.annotationprocessor.bind;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsvalidator.Validators;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.annotationprocessor.advice.Messages;
import net.bytebuddy.implementation.bind.annotation.StubValue;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.Set;

/**
 * Annotation processor for {@link StubValue} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>The annotated parameter must be a of type Object
 * </ul>
 */
public class StubValueProcessor extends AbstractByteBuddyAnnotationProcessor {


    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(StubValue.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(StubValue.class)) {


            VariableElement variableElement = ElementUtils.CastElement.castParameter(element);

            // check for annotated parameter type
            if (Validators.InAndExclusiveElementValidators.getRawTypeValidator(getFrameworkToolWrapper())
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

