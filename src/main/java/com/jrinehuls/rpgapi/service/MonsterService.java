package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.model.Monster;
import org.springframework.web.multipart.MultipartFile;

public interface MonsterService {

    Monster saveMonster(MonsterRequestDto monsterRequestDto);

    byte[] getImageById(Long id);
}
