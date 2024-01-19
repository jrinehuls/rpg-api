package com.jrinehuls.rpgapi.exception.conflict;

import org.springframework.http.HttpStatus;

public abstract class ResourceConflictException extends RuntimeException {

    private String field;

    public ResourceConflictException(String field) {
        super("The provided " + field + " is already in use");
        this.field = field;
    }

    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
