package br.com.tqi.exp.me.empresta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ClienteExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<List> processUnmergeException(final ConstraintViolationException ex) {

        List list = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }
}
