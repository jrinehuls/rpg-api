package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;

import java.util.Set;

public interface MonsterService {

    MonsterResponseDto saveMonster(MonsterRequestDto monsterRequestDto);

    MonsterResponseDto getMonster(Long id);

    MonsterResponseDto updateMonster(Long id, MonsterRequestDto monsterRequestDto);

    void deleteMonster(Long id);

    Set<SpellResponseDto> getSpells(Long id);

    byte[] getImageById(Long id);
}
