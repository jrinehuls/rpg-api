package com.jrinehuls.rpgapi.exception.badrequest;

public class UserBadRequestException extends RuntimeException {

    private final int statusCode = 400;

    public UserBadRequestException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
