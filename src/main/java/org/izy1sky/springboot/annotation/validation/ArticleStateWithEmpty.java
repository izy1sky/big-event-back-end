package org.izy1sky.springboot.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {ArticleStateWithEmptyConstraintValidator.class}
)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticleStateWithEmpty {
    String message() default "state的值只能是空或者'草稿'或者'已发布'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
