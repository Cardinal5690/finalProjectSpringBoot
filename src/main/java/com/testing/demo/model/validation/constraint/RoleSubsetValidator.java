package com.testing.demo.model.validation.constraint;

import com.testing.demo.model.entity.type.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleSubsetValidator implements ConstraintValidator<RoleSubset, Role> {
    private Role[] subset;

    @Override
    public void initialize(RoleSubset constraintAnnotation) {
        subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
        return role == null || Arrays.asList(subset).contains(role);
    }
}
