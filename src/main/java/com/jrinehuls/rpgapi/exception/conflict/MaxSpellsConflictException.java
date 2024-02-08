package com.jrinehuls.rpgapi.exception.conflict;

public class MaxSpellsConflictException extends ExceedsLimitConflictException{

    public MaxSpellsConflictException(int count) {
        super("Spell count cannot exceed " + count);
    }

}
