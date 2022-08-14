package be.bstorm.akimts.rest.bxl.validation.constraint;

import be.bstorm.akimts.rest.bxl.validation.validator.ReservationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ReservationValidator.class)
public @interface ReservationValid {

    String message() default "reservation invalide";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
