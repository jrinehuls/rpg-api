package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellRequestDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;

import java.util.Set;

public interface SpellService {

    SpellResponseDto saveSpell(SpellRequestDto spellRequestDto);
    SpellResponseDto getSpell(Long id);
    SpellResponseDto updateSpell(Long id, SpellRequestDto spellRequestDto);
    void deleteSpell(Long id);

    Set<MonsterResponseDto> getMonsters(Long id);

}
