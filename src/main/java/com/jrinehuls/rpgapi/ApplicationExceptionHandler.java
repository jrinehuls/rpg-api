package com.jrinehuls.rpgapi;

import com.jrinehuls.rpgapi.exception.ErrorResponse;
import com.jrinehuls.rpgapi.exception.notfound.MonsterNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> errors = new HashMap<>();
        for (ObjectError objectError: ex.getBindingResult().getAllErrors()) {
            FieldError error = (FieldError) objectError;
            String key = error.getField();
            String value = error.getDefaultMessage();
            this.updateMap(errors, key, value);
        }
        ErrorResponse errorResponse = new ErrorResponse(errors);
        errorResponse.setMessage("Invalid data supplied");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MonsterNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleInvalidDefinitionException(MonsterNotFoundException ex) {
        String message = ex.getMessage();
        ErrorResponse response = new ErrorResponse(null);
        response.setMessage(message);
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> handleInvalidDefinitionException(DataIntegrityViolationException ex) {
        String constraintName = ((ConstraintViolationException) ex.getCause()).getConstraintName();
        ErrorResponse response = new ErrorResponse(null);
        response.setMessage(constraintName);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*
    @ExceptionHandler({InvalidDefinitionException.class})
    public ResponseEntity<ErrorResponse> handleInvalidDefinitionException(InvalidDefinitionException ex) {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(ex.getMessage());
        ErrorResponse response = new ErrorResponse(messages);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    */

    private void updateMap(Map<String, List<String>> errors, String key, String value) {
        if (!errors.containsKey(key)) {
            List <String> values = new ArrayList<>();
            values.add(value);
            errors.put(key, values);
        } else {
            errors.get(key).add(value);
        }
    }

}
