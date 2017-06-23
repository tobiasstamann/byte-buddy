package net.bytebuddy.annotationprocessor.bind;

import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.annotationprocessor.advice.Messages;
import net.bytebuddy.implementation.bind.annotation.This;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Annotation processor for {@link This} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item> None
 * </ul>
 * <p/>
 * It doesn't really make sense to annotate primitive types, so in this case an error will be triggered
 */
public class ThisProcessor extends AbstractByteBuddyAnnotationProcessor {


    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(This.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {


        for (Element element : roundEnv.getElementsAnnotatedWith(This.class)) {

            // check if types are matching
            if (getTypeUtils().checkTypeKind().isPrimitive(element.asType())) {
                getMessager().error(element, Messages.COMMON__PARAMETER_MUST_NOT_HAVE_PRIMITIVE_TYPE.getMessage(), This.class.getSimpleName());
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
