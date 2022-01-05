package br.com.tqi.exp.me.empresta.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
interface para a criação de validação pela data inicial
não deve ser mais do que 3 meses da data atual - regra de negócio
*/

@Constraint(validatedBy = Regra3MesesValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Regra3Meses {
    String message() default "A data inicial deve ser daqui a 3 meses, no máximo ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
