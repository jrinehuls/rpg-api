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
            String field = ExceptionParser.getUniqueConstraintField(e, Monster.class);
            throw new MonsterConflictException(field);
        }
        return monsterMapper.mapMonsterToDto(savedMonster);
    }

    @Override
    public MonsterResponseDto getMonster(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        return monsterMapper.mapMonsterToDto(monster);
    }

    @Override
    public MonsterResponseDto updateMonster(Long id, MonsterRequestDto monsterRequestDto) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        Monster updatedMonster = monsterMapper.mapDtoToMonster(monsterRequestDto);
        updatedMonster.setId(monster.getId());
        Monster savedMonster;
        try {
            savedMonster = monsterRepository.save(updatedMonster);
        } catch (DataIntegrityViolationException e) {
            String field = ExceptionParser.getUniqueConstraintField(e, Monster.class);
            throw new MonsterConflictException(field);
        }
        return monsterMapper.mapMonsterToDto(savedMonster);
    }

    @Override
    public void deleteMonster(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        monsterRepository.delete(monster);
    }

    @Override
    public byte[] getImageById(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        return monster.getImage();
    }

}
