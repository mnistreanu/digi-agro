package com.arobs.config;

import com.arobs.exceptions.ApplicationException;
import com.arobs.model.rest.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public final ResponseEntity<ErrorDetails> handleAppException(ApplicationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getClass().getName(), ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
