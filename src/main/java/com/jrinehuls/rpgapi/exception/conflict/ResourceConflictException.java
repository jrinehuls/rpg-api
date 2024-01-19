package com.jrinehuls.rpgapi.exception.conflict;

import org.springframework.http.HttpStatus;

public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
