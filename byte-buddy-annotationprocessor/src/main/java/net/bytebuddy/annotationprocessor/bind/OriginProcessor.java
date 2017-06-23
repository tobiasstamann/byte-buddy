package net.bytebuddy.annotationprocessor.bind;

import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsvalidator.Validator;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.annotationprocessor.advice.Messages;
import net.bytebuddy.implementation.bind.annotation.Origin;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * Annotation processor for {@link Origin} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>The annotated parameter must be a String
 * <item>a constant representing the Class
 * <item>a constant representing the Method
 * <item>a constant representing the Constructor
 * <item>a java.lang.reflect.Executable
 * <item>int for modifiers
 * <item>a java.lang.invoke.MethodHandle (>= java 7)
 * <item>a java.lang.invoke.MethodType (>= java 7)
 * </ul>
 */
public class OriginProcessor extends AbstractByteBuddyAnnotationProcessor {


    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Origin.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Origin.class)) {


            // check if types are matching
            if (ElementUtils.CheckKindOfElement.isParameter(element)) {

                VariableElement variableElement = ElementUtils.CastElement.castParameter(element);

                // check for annotated parameter type
                if (Validator.getTypeValidator(frameworkToolWrapper)
                        .hasNoneOf(variableElement, Class.class, String.class, Method.class, Constructor.class, Executable.class, int.class)) {

                    // Some restrictions does only work with java 7, so get the type mirrors for corresponding types
                    TypeMirror methodHandleTypeMirror = getTypeUtils().getTypeMirror("java.lang.invoke.MethodHandle");
                    TypeMirror methodTypeTypeMirror = getTypeUtils().getTypeMirror("java.lang.invoke.MethodType");
                    if (methodHandleTypeMirror != null && methodTypeTypeMirror != null) {
                        if (!getTypeUtils().isAssignableTo(variableElement.asType(), methodHandleTypeMirror) && !getTypeUtils().isAssignableTo(variableElement.asType(), methodTypeTypeMirror)) {
                            getMessager().error(variableElement, Messages.ORIGIN__INVALID_TYPE.getMessage());
                        }
                    } else {
                        getMessager().error(variableElement, Messages.ORIGIN__INVALID_TYPE.getMessage());
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

