package com.jrinehuls.rpgapi.exception.conflict;

public class UserConflictException extends ResourceConflictException {

    public UserConflictException(String field) {
        super(field);
    }

}
