package io.davlac.drawingapp.context.canvascontent.service.impl.validator;

import io.davlac.drawingapp.context.canvascontent.service.ValidatorService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JavaxValidatorService implements ValidatorService {

    private final ValidatorFactory factory;

    public JavaxValidatorService(ValidatorFactory factory) {
        this.factory = factory;
    }

    @Override
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
