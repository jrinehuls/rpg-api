package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.spell.SpellRequestDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;

public interface SpellService {

    SpellResponseDto saveSpell(SpellRequestDto spellRequestDto);

}
