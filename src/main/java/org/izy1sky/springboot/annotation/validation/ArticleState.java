package org.izy1sky.springboot.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {ArticleStateConstraintValidator.class}
)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticleState {
    String message() default "state的值只能是'草稿'或者'已发布'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
