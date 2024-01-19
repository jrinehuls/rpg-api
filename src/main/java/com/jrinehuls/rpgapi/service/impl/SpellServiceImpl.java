package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.spell.SpellRequestDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.entity.Spell;
import com.jrinehuls.rpgapi.exception.conflict.SpellConflictException;
import com.jrinehuls.rpgapi.repository.SpellRepository;
import com.jrinehuls.rpgapi.service.SpellService;
import com.jrinehuls.rpgapi.util.ExceptionParser;
import com.jrinehuls.rpgapi.util.mapper.SpellMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpellServiceImpl implements SpellService {

    SpellRepository spellRepository;
    SpellMapper spellMapper;

    @Override
    public SpellResponseDto saveSpell(SpellRequestDto spellRequestDto) {
        Spell spell = spellMapper.mapDtoToSpell(spellRequestDto);
        Spell savedSpell;
        try {
            savedSpell = spellRepository.save(spell);
        } catch (DataIntegrityViolationException e) {
            String field = ExceptionParser.getUniqueConstraintField(e, Spell.class);
            throw new SpellConflictException(field);
        }
        return spellMapper.mapSpellToDto(savedSpell);
    }
}
