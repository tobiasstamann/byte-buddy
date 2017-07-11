package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsvalidator.Validators;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.Origin} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>The annotated parameter must be a String
 * <item>a constant representing the Class
 * <item>a constant representing the Method
 * <item>a constant representing the Constructor
 * <item>a java.lang.reflect.Executable
 * </ul>
 */
public class OriginProcessor extends AbstractByteBuddyAnnotationProcessor {


    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.Origin.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.Origin.class)) {


            // check if types are matching
            if (ElementUtils.CheckKindOfElement.isParameter(element)) {

                // check if parent is either annotated Advice.OnMethodEnter or Advice.OnMethodExit
                this.checkIfEnclosingMethodIsAnnotatatedWithNoneOf(element, Messages.COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD, Advice.Origin.class, Advice.OnMethodEnter.class, Advice.OnMethodExit.class);


                VariableElement variableElement = ElementUtils.CastElement.castParameter(element);

                // check for annotated parameter type
                if (Validators.InAndExclusiveElementValidators.getRawTypeValidator(getFrameworkToolWrapper())
                        .hasNoneOf(variableElement, Class.class, String.class, Method.class, Constructor.class, Executable.class)) {
                    getMessager().error(variableElement, Messages.ORIGIN__INVALID_TYPE.getMessage());
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

