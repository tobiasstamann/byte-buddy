package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.This;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.This} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>None
 * </ul>
 */
public class ThisProcessor extends AbstractByteBuddyAnnotationProcessor {

    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.This.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {


        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.This.class)) {


            // check if parent is either annotated Advice.OnMethodEnter or Advice.OnMethodExit
            this.checkIfEnclosingMethodIsAnnotatatedWithNoneOf(element, Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD, Advice.This.class, Advice.OnMethodEnter.class, Advice.OnMethodExit.class);


            // check if types are matching
            if (getTypeUtils().doCheckTypeKind().isPrimitive(element.asType())) {
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
