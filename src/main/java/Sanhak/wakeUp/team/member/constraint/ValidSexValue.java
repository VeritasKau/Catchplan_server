package Sanhak.wakeUp.team.member.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SexValueValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSexValue {
    String message() default "유효하지 않은 성별. 'M' 또는 'F' 중 하나를 선택.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}