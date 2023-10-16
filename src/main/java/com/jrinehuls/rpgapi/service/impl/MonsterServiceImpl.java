package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.MonsterDto;
import com.jrinehuls.rpgapi.model.Monster;
import com.jrinehuls.rpgapi.repository.MonsterRepository;
import com.jrinehuls.rpgapi.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    MonsterRepository monsterRepository;

    @Override
    public Monster saveMonster(MonsterDto monsterDto) {
        byte[] bytes;
        try {
            bytes = monsterDto.getImage().getBytes();
        } catch (IOException e) {
            // monster.setImage(null);
            throw new RuntimeException(e);
        }
        Monster monster = new Monster();
        monster.setName(monsterDto.getName());
        monster.setImage(bytes);
        return monsterRepository.save(monster);
    }

}
