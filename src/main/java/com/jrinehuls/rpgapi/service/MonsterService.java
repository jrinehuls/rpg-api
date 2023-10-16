package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.model.Monster;
import org.springframework.web.multipart.MultipartFile;

public interface MonsterService {

    Monster saveMonster(Monster monster, MultipartFile file);
}
