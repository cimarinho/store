package br.com.store.order.config.exception;

import br.com.store.order.config.exception.interfaces.CustomGlobalException;
import br.com.store.order.config.exception.response.CustomErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler implements CustomGlobalException {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<CustomErrorResponse> error(DomainException ex) {
        return error(Arrays.asList(ex.getMessage()), ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().stream().forEach(error-> errors.add(error.getField() + ": " + error.getDefaultMessage()));
        return error(errors, ex);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<CustomErrorResponse> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().stream().forEach(error-> errors.add(error.getPropertyPath() + ": " + error.getMessage()));
        return error(errors, ex);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<CustomErrorResponse> handleConstraintViolation(Exception ex, WebRequest request) {
        return error(Arrays.asList(ex.getMessage()), ex);
    }
}
