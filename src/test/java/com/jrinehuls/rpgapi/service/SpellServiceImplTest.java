package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.entity.Spell;
import com.jrinehuls.rpgapi.exception.notfound.SpellNotFoundException;
import com.jrinehuls.rpgapi.repository.SpellRepository;
import com.jrinehuls.rpgapi.service.impl.SpellServiceImpl;
import com.jrinehuls.rpgapi.util.mapper.MonsterMapper;
import com.jrinehuls.rpgapi.util.mapper.SpellMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpellServiceImplTest {

    @Mock
    private SpellRepository spellRepository;

    @Mock
    private SpellMapper spellMapper;

    @Mock
    private MonsterMapper monsterMapper;

    @InjectMocks
    private SpellServiceImpl spellService;

    @Test
    void getSpellTest() {
        Long id = 0L;
        Spell spell = new Spell();
        when(spellRepository.findById(id)).thenReturn(Optional.of(spell));
        spellService.getSpell(id);
        verify(spellMapper, times(1)).mapSpellToDto(spell);
    }

}
