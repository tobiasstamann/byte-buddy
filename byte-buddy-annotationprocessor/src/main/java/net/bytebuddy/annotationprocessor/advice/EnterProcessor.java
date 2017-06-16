package net.bytebuddy.annotationprocessor.advice;

import de.holisticon.annotationprocessortoolkit.filter.FluentElementFilter;
import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsfilter.Filter;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsvalidator.Validator;
import net.bytebuddy.annotationprocessor.AbstractByteBuddyAnnotationProcessor;
import net.bytebuddy.asm.Advice;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.List;
import java.util.Set;

/**
 * Annotation processor for {@link net.bytebuddy.asm.Advice.Enter} annotation.
 * <p/>
 * According to the documentation there are the following constraints on the usage of this annotation:
 * <ul>
 * <item>must be used inside method annotated with {@link net.bytebuddy.asm.Advice.OnMethodExit}
 * <item>(just cause warning) method annotated with {@link net.bytebuddy.asm.Advice.OnMethodEnter} must be present in advice class
 * <item>(just cause warning) type must match to corresponding {@link net.bytebuddy.asm.Advice.OnMethodEnter} return value type
 * </ul>
 */
public class EnterProcessor extends AbstractByteBuddyAnnotationProcessor {

    /**
     * the supported annotation types.
     */
    private static final Set<String> SUPPORTED_ANNOTATION_TYPES = createSupportedAnnotationSet(Advice.Enter.class);


    /**
     * @inheritDoc
     */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Advice.Enter.class)) {

            if (ElementUtils.CheckKindOfElement.isParameter(element)) {

                VariableElement parameterElement = ElementUtils.CastElement.castParameter(element);

                // check if parent is either annotated Advice.OnMethodExit
                Element enclosingElement = element.getEnclosingElement();
                if (ElementUtils.CheckKindOfElement.isMethod(enclosingElement)) {

                    // check if parent method is annotated with Advice.OnMethodExit
                    if (Validator.ANNOTATION_VALIDATOR.getValidator().hasNoneOf(enclosingElement, Advice.OnMethodExit.class)) {
                        getMessager().warning(element, Messages.COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.getMessage(), "Advice." + EnterProcessor.class.getSimpleName());
                    }

                }


                // now check for existence of method annotated with OnMethodEnter
                FluentElementFilter<Element> elementFilter = FluentElementFilter.createFluentFilter(
                        (List<Element>) element.getEnclosingElement().getEnclosingElement().getEnclosedElements()
                )
                        .applyFilter(Filter.ELEMENT_KIND_FILTER).filterByOneOf(ElementKind.METHOD)
                        .applyFilter(Filter.ANNOTATION_FILTER).filterByOneOf(Advice.OnMethodEnter.class);

                if (elementFilter.isEmpty()) {

                    getMessager().warning(element, Messages.ENTER__NO_ON_METHOD_ENTER_METHOD_DETECTED.getMessage());

                } else if (elementFilter.hasMultipleElements()) {

                    getMessager().warning(element, Messages.ENTER__AMBIGUOUS_ONMETHOD_ENTER.getMessage());

                } else {

                    ExecutableElement onMethodEnterExecutableElement = ElementUtils.CastElement.castMethod(elementFilter.getResult().get(0));

                    // check if types are assignable
                    if (!getTypeUtils().getTypes().isAssignable(onMethodEnterExecutableElement.getReturnType(), parameterElement.asType())) {
                        getMessager().warning(element, Messages.ENTER__INCOMPATIBLE_TYPE.getMessage());
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
