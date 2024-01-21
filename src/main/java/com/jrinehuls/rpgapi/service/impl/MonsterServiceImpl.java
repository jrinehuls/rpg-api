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

    private MonsterRepository monsterRepository;
    private MonsterMapper monsterMapper;

    @Override
    public MonsterResponseDto saveMonster(MonsterRequestDto monsterRequestDto) {
        Monster monster = monsterMapper.mapDtoToMonster(monsterRequestDto);
        try {
            Monster savedMonster = monsterRepository.save(monster);
            return monsterMapper.mapMonsterToDto(savedMonster);
        } catch (DataIntegrityViolationException e) {
            String field = ExceptionParser.getUniqueConstraintField(e, Monster.class);
            throw new MonsterConflictException(field);
        }
    }

    @Override
    public MonsterResponseDto getMonster(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        return monsterMapper.mapMonsterToDto(monster);
    }

    @Override
    public MonsterResponseDto updateMonster(Long id, MonsterRequestDto monsterRequestDto) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        try {
            Monster updatedMonster = monsterMapper.mapDtoToMonster(monsterRequestDto);
            updatedMonster.setId(monster.getId());
            Monster savedMonster = monsterRepository.save(updatedMonster);
            return monsterMapper.mapMonsterToDto(savedMonster);
        } catch (DataIntegrityViolationException e) {
            String field = ExceptionParser.getUniqueConstraintField(e, Monster.class);
            throw new MonsterConflictException(field);
        }
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
