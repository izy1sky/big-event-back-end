package org.sunshiyi.springboot.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class ArticleStateWithEmptyConstraintValidator implements ConstraintValidator<ArticleStateWithEmpty, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !StringUtils.hasLength(s) || "已发布".equals(s) || "草稿".equals(s);
    }
}
