package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;

public interface MonsterService {

    MonsterResponseDto saveMonster(MonsterRequestDto monsterRequestDto);

    byte[] getImageById(Long id);
}
