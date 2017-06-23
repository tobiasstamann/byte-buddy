package net.bytebuddy.annotationprocessor;

import de.holisticon.annotationprocessortoolkit.AbstractAnnotationProcessor;
import de.holisticon.annotationprocessortoolkit.filter.FluentElementFilter;
import de.holisticon.annotationprocessortoolkit.tools.ElementUtils;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsfilter.Filter;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsvalidator.Validator;
import net.bytebuddy.annotationprocessor.advice.Messages;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Abstract base class that provides for building annotation processors.
 */
public abstract class AbstractByteBuddyAnnotationProcessor extends AbstractAnnotationProcessor {

    /**
     * Checks if enclosing method is annotated with passed annotations.
     *
     * @param element            The parameter element to use as a base for checks
     * @param message            The warning messsage to use
     * @param affectedAnnotation The annotation type that is currently processed
     * @param annotations        The annotations to check for on enclosed method
     */
    public void checkIfEnclosingMethodIsAnnotatatedWithNoneOf(Element element, Messages message, Class<? extends Annotation> affectedAnnotation, Class<? extends Annotation>... annotations) {
        Element enclosingElement = element.getEnclosingElement();
        if (ElementUtils.CheckKindOfElement.isMethod(enclosingElement)) {

            if (Validator.ANNOTATION_VALIDATOR.getValidator().hasNoneOf(enclosingElement, annotations)) {
                getMessager().warning(element, message.getMessage(), "Advice." + affectedAnnotation.getSimpleName());
            }
        }
    }


    /**
     * Validated if passed annotation is used on multiple methods of enclosing type of passed methods Element.
     *
     * @param elementUnderValidation the element to validate
     * @param annotation             The annotation to look for
     */
    public void checkForAmbiguousUsageOfAnnotationsOnMultipleMethods(Element elementUnderValidation, Class<? extends Annotation> annotation) {


        if (FluentElementFilter.createFluentFilter((List<Element>) elementUnderValidation.getEnclosingElement().getEnclosedElements())
                .applyFilter(Filter.ELEMENT_KIND_FILTER).filterByOneOf(ElementKind.METHOD)
                .applyFilter(Filter.ANNOTATION_FILTER).filterByOneOf(annotation)
                .hasMultipleElements()) {
            getMessager().error(elementUnderValidation, Messages.COMMON__AMBIGUOUS_ON_MESSAGE_ENTER_OR_EXIT_METHOD.getMessage(), "Advice." + annotation.getSimpleName());
        }

    }


}
