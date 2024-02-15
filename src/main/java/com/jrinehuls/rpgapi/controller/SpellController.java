package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellRequestDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.entity.Spell;
import com.jrinehuls.rpgapi.service.SpellService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/spell")
@AllArgsConstructor
@CrossOrigin("*")
public class SpellController {

    private final SpellService spellService;

    @PostMapping(value = "")
    public ResponseEntity<SpellResponseDto> createSpell(@Valid @RequestBody SpellRequestDto dto) {
        return new ResponseEntity<>(spellService.saveSpell(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SpellResponseDto> getSpell(@PathVariable("id") Long id) {
        return new ResponseEntity<>(spellService.getSpell(id), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SpellResponseDto>> getAllSpells() {
        return new ResponseEntity<>(spellService.getAllSpells(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SpellResponseDto> updateSpell(@PathVariable("id") Long id, @Valid @RequestBody SpellRequestDto dto) {
        return new ResponseEntity<>(spellService.updateSpell(id, dto), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> updateSpell(@PathVariable("id") Long id) {
        spellService.deleteSpell(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/monsters")
    public ResponseEntity<Set<MonsterResponseDto>> getMonsters(@PathVariable("id") Long id) {
        return new ResponseEntity<>(spellService.getMonsters(id), HttpStatus.OK);
    }

}
