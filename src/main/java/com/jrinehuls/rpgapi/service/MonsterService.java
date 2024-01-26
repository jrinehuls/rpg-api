package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;

import java.util.List;
import java.util.Set;

public interface MonsterService {

    MonsterResponseDto saveMonster(MonsterRequestDto monsterRequestDto);

    MonsterResponseDto getMonster(Long id);

    List<MonsterResponseDto> getAllMonsters();

    MonsterResponseDto updateMonster(Long id, MonsterRequestDto monsterRequestDto);

    void deleteMonster(Long id);

    Set<SpellResponseDto> getSpells(Long id);

    Set<SpellResponseDto> addSpellToMonster(Long monsterId, Long spellId);

    byte[] getImageById(Long id);
}
