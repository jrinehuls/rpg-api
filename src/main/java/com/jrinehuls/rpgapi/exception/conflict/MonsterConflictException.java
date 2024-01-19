package com.jrinehuls.rpgapi.exception.conflict;

public class MonsterConflictException extends ResourceConflictException {

    public MonsterConflictException(String field) {
        super(field);
    }

}
