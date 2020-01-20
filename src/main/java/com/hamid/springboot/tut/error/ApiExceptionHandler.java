package com.hamid.springboot.tut.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


/**
 * @author Hamid Ait Brahim
 * @Created 09/01/2020
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ErrorDetails> handleApiException(ApiBaseException ex, WebRequest request)
    {
        ErrorDetails details = new ErrorDetails(ex.getMessage(),request.getDescription(false));
        return  new ResponseEntity<>(details,ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError validationError = new ValidationError();
        validationError.setUri(request.getDescription(false));

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError error: fieldErrors)
        {
           validationError.addError(error.getDefaultMessage());
        }
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }
}
