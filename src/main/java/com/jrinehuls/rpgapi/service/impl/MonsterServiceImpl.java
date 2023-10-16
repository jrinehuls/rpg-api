package com.jrinehuls.rpgapi.service.impl;

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
    public Monster saveMonster(Monster monster, MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            // monster.setImage(null);
            throw new RuntimeException(e);
        }
        monster.setImage(bytes);
        return monsterRepository.save(monster);
    }

}
