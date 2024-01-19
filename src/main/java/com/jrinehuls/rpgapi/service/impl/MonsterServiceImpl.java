package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.exception.conflict.MonsterConflictException;
import com.jrinehuls.rpgapi.exception.notfound.MonsterNotFoundException;
import com.jrinehuls.rpgapi.entity.Monster;
import com.jrinehuls.rpgapi.repository.MonsterRepository;
import com.jrinehuls.rpgapi.service.MonsterService;
import com.jrinehuls.rpgapi.util.ExceptionParser;
import com.jrinehuls.rpgapi.util.mapper.MonsterMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonsterServiceImpl implements MonsterService {

    MonsterRepository monsterRepository;
    MonsterMapper monsterMapper;

    @Override
    public MonsterResponseDto saveMonster(MonsterRequestDto monsterRequestDto) {
        Monster monster = monsterMapper.mapDtoToMonster(monsterRequestDto);
        Monster savedMonster;
        try {
            savedMonster = monsterRepository.save(monster);
        } catch (DataIntegrityViolationException e) {
            String field = ExceptionParser.getField(e);
            throw new MonsterConflictException(field);
        }
        return monsterMapper.mapMonsterToDto(savedMonster);
    }

    @Override
    public byte[] getImageById(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        return monster.getImage();
    }

}
