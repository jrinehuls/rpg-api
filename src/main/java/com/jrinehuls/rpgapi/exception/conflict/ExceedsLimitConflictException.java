package com.jrinehuls.rpgapi.exception.conflict;

import org.springframework.http.HttpStatus;

public abstract class ExceedsLimitConflictException extends RuntimeException {

    public ExceedsLimitConflictException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }

}
