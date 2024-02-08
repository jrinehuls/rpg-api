package com.jrinehuls.rpgapi.exception.notfound;

public class MonsterSpellNotFoundException extends ResourceNotFoundException {

    public MonsterSpellNotFoundException(Long monsterId, Long spellId) {
        super(String.format("Monster with id: %d does not know spell with id: %d", monsterId, spellId));
    }

}
