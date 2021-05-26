package com.testing.demo.model.validation.constraint;

import com.testing.demo.model.entity.type.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusSubsetValidator implements ConstraintValidator<StatusSubset, Status> {
    Status[] subset;

    @Override
    public void initialize(StatusSubset constraintAnnotation) {
        subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Status status, ConstraintValidatorContext constraintValidatorContext) {
        return status == null || Arrays.asList(subset).contains(status);
    }
}
