package net.bytebuddy.annotationprocessor.advice;

import net.bytebuddy.asm.Advice;

/**
 * Messages used by annotation processors of Advice annotations.
 */
public enum Messages {

    /**
     * COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.
     */
    COMMON__NO_ON_METHOD_ENTER_AND_EXIT_ANNOTATION_ON_ENCLOSING_METHOD("COMMON_01", "Enclosing methods of parameters annotated with annotation ${0} should be annotated with Advice." + Advice.OnMethodEnter.class.getSimpleName() + " or Advice." + Advice.OnMethodExit.class.getSimpleName()),
    /**
     * COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD.
     */
    COMMON__NO_ON_METHOD_EXIT_ANNOTATION_ON_ENCLOSING_METHOD("COMMON_02", "Enclosing methods of parameters annotated with annotation ${0} should be annotated with Advice.OnMethodExit"),
    /**
     * COMMON__AMBIGUOUS_ON_MESSAGE_ENTER_OR_EXIT_METHOD.
     */
    COMMON__AMBIGUOUS_ON_MESSAGE_ENTER_OR_EXIT_METHOD("COMMON_03", "Only one method in a class must be annotated with annotation ${0}"),
    /**
     * COMMON__METHOD_MUST_BE_STATIC.
     */
    COMMON__METHOD_MUST_BE_STATIC("COMMON_04", "Method annotated with Advice.${0} must be declared as static"),
    /**
     * COMMON__PARAMETER_MUST_NOT_HAVE_PRIMITIVE_TYPE.
     */
    COMMON__PARAMETER_MUST_NOT_HAVE_PRIMITIVE_TYPE("COMMON_05", "Parameter annotated with annotation ${0} must not have a primitive type."),
    /**
     * ALL_ARGUMENTS__ANNOTATED_PARAMETER_SHOULD_BE_OBJECT_ARRAY.
     */
    ALL_ARGUMENTS__ANNOTATED_PARAMETER_SHOULD_BE_OBJECT_ARRAY("ALLARGUMENTS_01", "Parameter annotated with annotation Advice." + Advice.AllArguments.class.getSimpleName()

            + " should be of type Object[] to achieve best compatibility of advice\""),

    /**
     * ALL_ARGUMENTS__ANNOTATED_PARAMETER_MUST_BE_ARRAY.
     */
    ALL_ARGUMENTS__ANNOTATED_PARAMETER_MUST_BE_ARRAY("ALLARGUMENTS_02", "Parameter Annotated with annotation Advice." + Advice.AllArguments.class.getSimpleName()

            + " must be an array (f.e Object[] for best compatibility)"),
    /**
     * ENTER__AMBIGUOUS_ONMETHOD_ENTER.
     */
    ENTER__AMBIGUOUS_ONMETHOD_ENTER("ENTER_01", "Parameter is annotated with Advice." + Advice.Enter.class.getSimpleName() + ", but there are multiple methods annotated with Advice." + Advice.OnMethodEnter.class.getSimpleName() + " present in enclosing type, so return type is ambiguous."),
    /**
     * ENTER__INCOMPATIBLE_TYPE.
     */
    ENTER__INCOMPATIBLE_TYPE("ENTER_02", "Return type of related method annotated with Advice." + Advice.OnMethodEnter.class.getSimpleName() + " annotation is not assignable to parameter type annotated with Advice." + Advice.Enter.class.getSimpleName()),
    /**
     * ENTER__NO_ON_METHOD_ENTER_METHOD_DETECTED.
     */
    ENTER__NO_ON_METHOD_ENTER_METHOD_DETECTED("ENTER_03", "Couldn't find method annotated with Advice." + Advice.OnMethodEnter.class.getSimpleName() + " in enclosing type"),
    /**
     * ORIGIN__INVALID_TYPE.
     */
    ORIGIN__INVALID_TYPE("ORIGIN_01", "Annotated Parameter with annotation Advice." + Advice.Origin.class.getSimpleName() + " has to be of one of the following types: Class, String, Method, Constructor, Executable"),
    /**
     * STUBVALUE__INCOMPATIBLE_TYPE.
     */
    STUBVALUE__INCOMPATIBLE_TYPE("STUBVALUE_01", "Annotated Parameter with annotation Advice." + Advice.StubValue.class.getSimpleName() + " has to be of one of the following types: Object"),
    /**
     * THROWN__SHOULD_BE_THROWABLE.
     */
    THROWN__SHOULD_BE_THROWABLE("THROWN_01", "It is recommended for Parameters annotated with annotation Advice." + Advice.Thrown.class.getSimpleName() + " to be of type Throwable for best compatibility"),
    /**
     * THROWN__SHOULD_AT_LEAST_EXTEND_THROWABLE.
     */
    THROWN__SHOULD_AT_LEAST_EXTEND_THROWABLE("THROWN_02", "Parameter annotated with annotation Advice." + Advice.Thrown.class.getSimpleName() + " must be at least assignable to Throwable");

    /**
     * Flag that defines if messages codes will be written as part of the message.
     */
    private static boolean printMessageCodes = false;

    /**
     * the message code.
     */
    private final String code;
    /**
     * the message text.
     */
    private final String message;

    /**
     * Constructor.
     *
     * @param code    the message code
     * @param message the message text
     */
    private Messages(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the code of the message.
     *
     * @return the message code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the message text.
     *
     * @return the message text
     */
    public String getMessage() {
        return (printMessageCodes ? "[" + code + "] : " : "") + message;
    }


    /**
     * Allows toggling if message codes should be printed.
     *
     * @param printMessageCodes defines if message codes should be part of the message text
     */
    public static void setPrintMessageCodes(boolean printMessageCodes) {
        Messages.printMessageCodes = printMessageCodes;
    }

}
