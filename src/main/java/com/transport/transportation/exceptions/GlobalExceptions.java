package com.transport.transportation.exceptions;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptions {

    ResponseEntity<TransErrors> responseEntity = null;

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody
    public ErrorResponse constraintVoilation(RollbackException ex) {

        StringBuilder sb = new StringBuilder();
        if (ex.getCause() instanceof ConstraintViolationException) {

            ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();

            Set<ConstraintViolation<?>> sc = cve.getConstraintViolations();
            if (sc.size() > 0) {
                sc.forEach(exe -> {
                    if (exe instanceof ConstraintViolationImpl) {

                        sb.append(error(ex, HttpStatus.BAD_REQUEST,
                                exe.getPropertyPath() + " " + exe.getMessage()));

                    }
                });
            }
        }
        return new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), sb.toString());
    }


    @ExceptionHandler({JpaSystemException.class})
    @ResponseBody
    public ErrorResponse dbVoilations(Throwable ex) {
        Throwable exp = ex.getCause();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), exp.getCause().getMessage());
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse allOtherErrors(Throwable ex) {
        Throwable exp = ex.getCause();
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exp.getCause().getMessage());
    }

    private String error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return logRef;
    }

}
