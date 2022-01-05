package br.com.tqi.exp.me.empresta.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/*
implementação de @interface, para criar annotation
com a seguinte constraint:
data inicial não pode ser mais do que 3 meses da
data atual - regra de negócio
*/

public class Regra3MesesValidator  implements ConstraintValidator<Regra3Meses, LocalDate> {
    @Override
    public void initialize(Regra3Meses constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate hoje = LocalDate.now();
        return hoje.plusMonths(3).isAfter(localDate);
    }
}
