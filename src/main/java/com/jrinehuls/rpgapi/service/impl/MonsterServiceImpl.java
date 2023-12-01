package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.model.Monster;
import com.jrinehuls.rpgapi.repository.MonsterRepository;
import com.jrinehuls.rpgapi.service.MonsterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    MonsterRepository monsterRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Monster saveMonster(MonsterRequestDto monsterRequestDto) {
        Monster monster = modelMapper.map(monsterRequestDto, Monster.class);
        try {
            monster.setImage(monsterRequestDto.getImage().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return monsterRepository.save(monster);
    }

}
