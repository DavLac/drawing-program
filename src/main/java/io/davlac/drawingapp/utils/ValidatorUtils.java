package io.davlac.drawingapp.utils;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ValidatorUtils {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private ValidatorUtils() {
    }

    public static void init() {
        // Method empty to load this class at runtime (to show the Hibernate validator loading at the beginning)
    }

    public static void checkIfStringsAreTheSameTypeThanObjectFields(Class clazz, List<String> strings) {
        Field[] allFields = clazz.getDeclaredFields();

        IntStream.range(0, allFields.length)
                .forEach(index -> {
                            if (allFields[index].getType().isInstance(Integer.class)) {
                                tryToParseInteger(strings.get(index));
                            }
                        }
                );
    }

    public static void checkArgumentLength(List<String> arguments, Integer argsMinSize, ActionCommand actionCommand) {
        if (arguments.size() < argsMinSize) {
            throw new IllegalArgumentException(String.format("ERROR: Action '%s' - not enough arguments", actionCommand));
        }
    }

    public static void validate(Object object) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        if (!constraintViolations.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("ERROR: argument validation failed when create a Canvas : '%s'", constraintViolations.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList())));
        }
    }

    private static void tryToParseInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    String.format("ERROR: bad argument type to create a Canvas : '%s'", ex.getMessage()));
        }
    }
}
