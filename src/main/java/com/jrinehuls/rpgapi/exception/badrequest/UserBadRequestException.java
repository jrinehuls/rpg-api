package com.jrinehuls.rpgapi.exception.badrequest;


public class UserBadRequestException extends RuntimeException {

    public UserBadRequestException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return 400;
    }

}
