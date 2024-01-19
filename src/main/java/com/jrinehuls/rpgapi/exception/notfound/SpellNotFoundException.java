package com.jrinehuls.rpgapi.exception.notfound;

public class SpellNotFoundException extends ResourceNotFoundException {

    public SpellNotFoundException(Long id) {
        super("Spell with id '" + id + "' not found.");
    }
    
}
