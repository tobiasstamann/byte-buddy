package net.bytebuddy.annotationprocessor;

import de.holisticon.annotationprocessortoolkit.AbstractAnnotationProcessor;
import de.holisticon.annotationprocessortoolkit.filter.FluentElementFilter;
import de.holisticon.annotationprocessortoolkit.tools.characteristicsfilter.Filter;
import net.bytebuddy.annotationprocessor.advice.Messages;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Abstract base class that provides for building annotation processors.
 */
public abstract class AbstractByteBuddyAnnotationProcessor extends AbstractAnnotationProcessor {

    public void checkForAmbiguousUsageOfAnnotationsOnMultipleMethods(Element elementUnderValidation, Class<? extends Annotation> annotation) {


        if (FluentElementFilter.createFluentFilter((List<Element>) elementUnderValidation.getEnclosingElement().getEnclosedElements())
                .applyFilter(Filter.ELEMENT_KIND_FILTER).filterByOneOf(ElementKind.METHOD)
                .applyFilter(Filter.ANNOTATION_FILTER).filterByOneOf(annotation)
                .hasMultipleElements()) {
            getMessager().error(elementUnderValidation, Messages.COMMON__AMBIGUOUS_ON_MESSAGE_ENTER_OR_EXIT_METHOD.getMessage(), "Advice." + annotation.getSimpleName());
        }

    }


}
