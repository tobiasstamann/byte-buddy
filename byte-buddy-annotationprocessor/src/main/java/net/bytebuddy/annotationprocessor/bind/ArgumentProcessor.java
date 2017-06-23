package net.bytebuddy.annotationprocessor.bind;

import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.implementation.bind.annotation.Argument;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;


/**
 * Annotation processor for {@link Argument} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 */

public class ArgumentProcessor extends AbstractByteBuddyAnnotationProcessor {

    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Argument.class);

    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Argument.class)) {


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
