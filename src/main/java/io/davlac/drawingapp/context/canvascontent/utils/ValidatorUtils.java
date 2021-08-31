package io.davlac.drawingapp.context.canvascontent.utils;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorUtils {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private ValidatorUtils() {
    }

    public static void init() {
        // Method empty to load this class at runtime (to show the Hibernate validator loading message at the beginning)
    }

    public static void checkArgumentLength(List<String> arguments, ActionCommand actionCommand) {
        if (arguments.size() < actionCommand.getMinArgumentSize()) {
            throw new IllegalArgumentException(String.format("ERROR: Action '%s' - not enough arguments (%d)",
                    actionCommand, actionCommand.getMinArgumentSize()));
        }
    }

    public static void validateObjectConstraints(Object object) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        if (!constraintViolations.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("ERROR: argument validation failed when create a Canvas : '%s'", constraintViolations.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList())));
        }
    }
}
