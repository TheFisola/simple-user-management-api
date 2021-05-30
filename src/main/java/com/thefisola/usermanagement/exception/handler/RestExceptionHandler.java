package com.thefisola.usermanagement.exception.handler;

import com.thefisola.usermanagement.exception.BaseException;
import com.thefisola.usermanagement.exception.UnverifiedUserException;
import com.thefisola.usermanagement.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    public static Map<String, String> formatErrorMessage(MethodArgumentNotValidException methodArgumentNotValid) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : methodArgumentNotValid.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();
            if (fieldError.getField().contains("data.")) {
                fieldName = fieldName.replace("data.", "");
            }
            errors.put(fieldName, fieldError.getDefaultMessage());
        }
        return errors.keySet().isEmpty() ? null : errors;
    }

    private static ErrorResponse errorResponseBuilder(String message) {
        return errorResponseBuilder(message, null);
    }

    private static ErrorResponse errorResponseBuilder(String message, Map<String, String> validation) {
        return ErrorResponse.builder()
                .message(message)
                .validation(validation).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(errorResponseBuilder("Parameters not valid", formatErrorMessage(e)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(errorResponseBuilder(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException e) {
        return new ResponseEntity<>(errorResponseBuilder(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException e) {
        return new ResponseEntity<>(errorResponseBuilder("Similar record already exist"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>(errorResponseBuilder(e.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UnverifiedUserException.class)
    public ResponseEntity<ErrorResponse> handleException(UnverifiedUserException e) {
        return new ResponseEntity<>(errorResponseBuilder(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleException(BaseException e) {
        return new ResponseEntity<>(errorResponseBuilder(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(errorResponseBuilder("Something went wrong while trying to process your request"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
