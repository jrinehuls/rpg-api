package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellRequestDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.entity.Monster;
import com.jrinehuls.rpgapi.entity.Spell;
import com.jrinehuls.rpgapi.exception.conflict.SpellConflictException;
import com.jrinehuls.rpgapi.exception.notfound.MonsterNotFoundException;
import com.jrinehuls.rpgapi.exception.notfound.SpellNotFoundException;
import com.jrinehuls.rpgapi.repository.SpellRepository;
import com.jrinehuls.rpgapi.service.SpellService;
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
public class SpellServiceImpl implements SpellService {

    private final SpellRepository spellRepository;
    private final SpellMapper spellMapper;
    private final MonsterMapper monsterMapper;

    @Override
    public SpellResponseDto saveSpell(SpellRequestDto spellRequestDto) {
        Spell spell = new Spell();
        spellMapper.mapDtoToSpell(spellRequestDto, spell);
        try {
            Spell savedSpell = spellRepository.save(spell);
            return spellMapper.mapSpellToDto(savedSpell);
        } catch (DataIntegrityViolationException e) {
            String field = ExceptionParser.getUniqueConstraintField(e, Spell.class);
            throw new SpellConflictException(field);
        }
    }

    @Override
    public SpellResponseDto getSpell(Long id) {
        Spell spell = spellRepository.findById(id).orElseThrow(() -> new SpellNotFoundException(id));
        return spellMapper.mapSpellToDto(spell);
    }

    @Override
    public List<SpellResponseDto> getAllSpells() {
        List<Spell> spells = spellRepository.findAll();
        List<SpellResponseDto> spellDtos = new ArrayList<>();
        for (Spell spell : spells) {
            spellDtos.add(spellMapper.mapSpellToDto(spell));
        }
        return spellDtos;
    }

    @Override
    public SpellResponseDto updateSpell(Long id, SpellRequestDto spellRequestDto) {
        Spell spell = spellRepository.findById(id).orElseThrow(() -> new SpellNotFoundException(id));
        try {
            spellMapper.mapDtoToSpell(spellRequestDto, spell);
            Spell savedSpell = spellRepository.save(spell);
            return spellMapper.mapSpellToDto(savedSpell);
        } catch (DataIntegrityViolationException e) {
            String field = ExceptionParser.getUniqueConstraintField(e, Spell.class);
            throw new SpellConflictException(field);
        }
    }

    @Override
    public void deleteSpell(Long id) {
        Spell spell = spellRepository.findById(id).orElseThrow(() -> new SpellNotFoundException(id));
        spellRepository.delete(spell);
    }

    @Override
    public Set<MonsterResponseDto> getMonsters(Long id) {
        Spell spell = spellRepository.findById(id).orElseThrow(() -> new SpellNotFoundException(id));
        Set<Monster> monsters = spell.getMonsters();
        Set<MonsterResponseDto> monsterDtos = new HashSet<>();
        for (Monster monster: monsters) {
            monsterDtos.add(monsterMapper.mapMonsterToDto(monster));
        }
        return monsterDtos;
    }
}
