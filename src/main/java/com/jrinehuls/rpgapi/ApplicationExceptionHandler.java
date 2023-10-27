package com.jrinehuls.rpgapi;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.jrinehuls.rpgapi.exception.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidDefinitionException.class})
    public ResponseEntity<ErrorResponse> handleInvalidDefinitionException(InvalidDefinitionException ex) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorResponse response = new ErrorResponse(messages);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ArrayList<String> messages = new ArrayList<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            messages.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(messages), HttpStatus.BAD_REQUEST);
    }

}
