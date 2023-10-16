package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.MonsterDto;
import com.jrinehuls.rpgapi.model.Monster;
import org.springframework.web.multipart.MultipartFile;

public interface MonsterService {

    Monster saveMonster(MonsterDto monsterDto);
}
