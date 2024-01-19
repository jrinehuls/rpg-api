package com.jrinehuls.rpgapi.exception.conflict;

public class SpellConflictException extends ResourceConflictException {

    public SpellConflictException(String field) {
        super(field);
    }

}