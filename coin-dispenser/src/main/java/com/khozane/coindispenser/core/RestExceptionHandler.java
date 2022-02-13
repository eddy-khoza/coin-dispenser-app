package com.khozane.coindispenser.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<CustomException> handleValidationException(InvalidInputException e){

        String errorMessage = e.getMessage();
        CustomException customErrorResponse = new CustomException();
        customErrorResponse.setError(errorMessage);
        customErrorResponse.setLocalDateTime(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(customErrorResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<?> internalServerException(InternalServerException e){

        CustomException customErrorResponse = new CustomException();
        customErrorResponse.setError(e.getMessage());
        customErrorResponse.setLocalDateTime(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(customErrorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
