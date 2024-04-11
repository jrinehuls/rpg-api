package com.jrinehuls.rpgapi.exception.notfound;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
