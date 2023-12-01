package org.izy1sky.springboot.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ArticleStateConstraintValidator implements ConstraintValidator<ArticleState, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return "已发布".equals(s) || "草稿".equals(s);
    }
}
