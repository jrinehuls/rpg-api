package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.entity.Spell;
import com.jrinehuls.rpgapi.exception.conflict.MonsterConflictException;
import com.jrinehuls.rpgapi.exception.notfound.MonsterNotFoundException;
import com.jrinehuls.rpgapi.entity.Monster;
import com.jrinehuls.rpgapi.exception.notfound.SpellNotFoundException;
import com.jrinehuls.rpgapi.repository.MonsterRepository;
import com.jrinehuls.rpgapi.repository.SpellRepository;
import com.jrinehuls.rpgapi.service.MonsterService;
import com.jrinehuls.rpgapi.util.ExceptionParser;
import com.jrinehuls.rpgapi.util.mapper.MonsterMapper;
import com.jrinehuls.rpgapi.util.mapper.SpellMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MonsterServiceImpl implements MonsterService {

    private MonsterRepository monsterRepository;
    private MonsterMapper monsterMapper;
    private SpellRepository spellRepository;
    private SpellMapper spellMapper;

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
    public List<MonsterResponseDto> getAllMonsters() {
        Iterable<Monster> monsters = monsterRepository.findAll();
        List<MonsterResponseDto> monsterDtos = new ArrayList<>();
        for (Monster monster : monsters) {
            monsterDtos.add(monsterMapper.mapMonsterToDto(monster));
        }
        return monsterDtos;
    }

    @Override
    public MonsterResponseDto updateMonster(Long id, MonsterRequestDto monsterRequestDto) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        Monster savedMonster;
        Monster updatedMonster;
        try {
            try {
                updatedMonster = monsterMapper.mapDtoToMonster(monsterRequestDto);
            } catch (NullPointerException e) {
                updatedMonster = monsterMapper.mapDtoToMonster(monsterRequestDto, monster);
            }
            updatedMonster.setId(monster.getId());
            savedMonster = monsterRepository.save(updatedMonster);
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
    public Set<SpellResponseDto> getSpells(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        return this.getMonsterSpells(monster);
    }

    @Override
    public Set<SpellResponseDto> addSpellToMonster(Long monsterId, Long spellId) {
        Monster monster = monsterRepository.findById(monsterId).orElseThrow(() -> new MonsterNotFoundException(monsterId));
        Spell spell = spellRepository.findById(spellId).orElseThrow(() -> new SpellNotFoundException(spellId));
        monster.getSpells().add(spell);
        Monster savedMonster = monsterRepository.save(monster);
        return this.getMonsterSpells(savedMonster);
    }

    @Override
    public byte[] getImageById(Long id) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new MonsterNotFoundException(id));
        return monster.getImage();
    }

    private Set<SpellResponseDto> getMonsterSpells(Monster monster) {
        Set<Spell> spells = monster.getSpells();
        Set<SpellResponseDto> spellDtos = new HashSet<>();
        for (Spell spell: spells) {
            spellDtos.add(spellMapper.mapSpellToDto(spell));
        }
        return spellDtos;
    }

}
