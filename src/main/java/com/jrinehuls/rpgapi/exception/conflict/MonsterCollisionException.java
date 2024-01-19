package com.jrinehuls.rpgapi.exception.conflict;

public class MonsterCollisionException extends ResourceConflictException {

    String constraintName;

    public MonsterCollisionException(String field) {
        super(field + "is already in use");
    }

}
