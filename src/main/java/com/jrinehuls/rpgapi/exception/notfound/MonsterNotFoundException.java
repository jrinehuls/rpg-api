package com.jrinehuls.rpgapi.exception.notfound;

import org.springframework.http.HttpStatus;

public class MonsterNotFoundException extends ResourceNotFoundException {

    public MonsterNotFoundException(Long id) {
        super("Monster with id '" + id + "' not found.");
    }

}
