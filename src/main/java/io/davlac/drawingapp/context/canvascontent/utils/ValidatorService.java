package io.davlac.drawingapp.context.canvascontent.utils;

import io.davlac.drawingapp.context.inputcommand.model.ActionCommand;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidatorService {

    private final ValidatorFactory factory;

    public ValidatorService(ValidatorFactory factory) {
        this.factory = factory;
    }

    public static void checkArgumentLength(List<String> arguments, ActionCommand actionCommand) {
        if (arguments == null || arguments.size() < actionCommand.getMinArgumentSize()) {
            throw new IllegalArgumentException(String.format("ERROR: Action '%s' - not enough arguments (%d)",
                    actionCommand, actionCommand.getMinArgumentSize()));
        }
    }

    public boolean validateObjectConstraints(Object object) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        if (!constraintViolations.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("ERROR: argument validation failed when create a Canvas : '%s'", constraintViolations.stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.toList())));
        }

        return true;
    }
}
