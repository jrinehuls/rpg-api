package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.exception.notfound.MonsterNotFoundException;
import com.jrinehuls.rpgapi.model.Monster;
import com.jrinehuls.rpgapi.repository.MonsterRepository;
import com.jrinehuls.rpgapi.service.MonsterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        } catch (IOException | NullPointerException e) {
            monster.setImage(null);
        }

        return monsterRepository.save(monster);
    }

    @Override
    public byte[] getImageById(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        return monster.getImage();
    }

}
