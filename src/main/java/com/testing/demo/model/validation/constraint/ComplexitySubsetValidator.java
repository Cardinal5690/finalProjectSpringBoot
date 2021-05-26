package com.testing.demo.model.validation.constraint;


import com.testing.demo.model.entity.type.Complexity;
import com.testing.demo.model.validation.constraint.ComplexitySubset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ComplexitySubsetValidator implements ConstraintValidator<ComplexitySubset, Complexity> {
    private Complexity[] subset;

    @Override
    public void initialize(ComplexitySubset constraintAnnotation) {
        subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Complexity complexity, ConstraintValidatorContext constraintValidatorContext) {
        return complexity == null || Arrays.asList(subset).contains(complexity);
    }
}
