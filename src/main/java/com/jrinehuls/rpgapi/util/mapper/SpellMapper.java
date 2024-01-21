package com.jrinehuls.rpgapi.util.mapper;

import com.jrinehuls.rpgapi.dto.spell.SpellRequestDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.entity.Spell;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SpellMapper {

    public Spell mapDtoToSpell(SpellRequestDto spellDto) {
        Spell spell = new Spell();
        spell.setName(spellDto.getName());
        spell.setDescription(spellDto.getDescription());
        spell.setPower(spellDto.getPower());

        return spell;
    }

    public SpellResponseDto mapSpellToDto(Spell spell) {
        SpellResponseDto spellDto = new SpellResponseDto();
        spellDto.setId(spell.getId());
        spellDto.setName(spell.getName());
        spellDto.setDescription(spell.getDescription());
        spellDto.setPower(spell.getPower());

        return spellDto;
    }
}
