package io.davlac.drawingapp.context.canvascontent.service;

import io.davlac.drawingapp.config.AppConfig;
import io.davlac.drawingapp.context.canvascontent.service.impl.validator.JavaxValidatorService;
import io.davlac.drawingapp.context.canvascontent.service.testmodel.ConstraintTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
class JavaxValidatorServiceTest {

    @Autowired
    private ValidatorFactory factory;

    @Autowired
    private JavaxValidatorService javaxValidatorService;

    @Test
    void validateCreateCanvasRequest_withValidConstraintObject_shouldReturnTrue() {
        assertTrue(javaxValidatorService.validateObjectConstraints(new ConstraintTest("a", "abc")));
    }

    @Test
    void validateCreateCanvasRequest_withNotValidConstraintObject_shouldThrowError() {
        try {
            javaxValidatorService.validateObjectConstraints(new ConstraintTest("a", "a"));
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: argument validation failed when create a Canvas : '[size must be between 2 and 4]'", ex.getMessage());
        }
    }

    @Test
    void validateCreateCanvasRequest_withNotValidConstraintObject2_shouldThrowError() {
        try {
            javaxValidatorService.validateObjectConstraints(new ConstraintTest("", "ab"));
        } catch (IllegalArgumentException ex) {
            assertEquals("ERROR: argument validation failed when create a Canvas : '[must not be empty]'", ex.getMessage());
        }
    }

    @Test
    void validateCreateCanvasRequest_withNullObject_shouldThrowError() {
        try {
            javaxValidatorService.validateObjectConstraints(null);
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex.getMessage());
        }
    }
}
