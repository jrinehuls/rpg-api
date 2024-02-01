package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.service.MonsterService;
import com.jrinehuls.rpgapi.validation.groups.MonsterCreation;
import com.jrinehuls.rpgapi.validation.groups.MonsterUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/monster")
@CrossOrigin("*")
public class MonsterController {

    @Autowired
    private MonsterService monsterService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> saveMonster(@Validated({MonsterCreation.class})
            @ModelAttribute MonsterRequestDto monsterRequestDto) {
        return new ResponseEntity<>(monsterService.saveMonster(monsterRequestDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> getMonster(@PathVariable("id") Long id) {
        return new ResponseEntity<>(monsterService.getMonster(id), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MonsterResponseDto>> getAllMonsters() {
        return new ResponseEntity<>(monsterService.getAllMonsters(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> updateMonster(@PathVariable("id") Long id,
            @Validated({MonsterUpdate.class}) @ModelAttribute MonsterRequestDto monsterRequestDto) {
        return new ResponseEntity<>(monsterService.updateMonster(id, monsterRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMonster(@PathVariable("id") Long id) {
        monsterService.deleteMonster(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/spells")
    public ResponseEntity<Set<SpellResponseDto>> getSpells(@PathVariable("id") Long id) {
        return new ResponseEntity<>(monsterService.getSpells(id), HttpStatus.OK);
    }

    @PatchMapping("/{monsterId}/spell/{spellId}")
    public ResponseEntity<Set<SpellResponseDto>> addSpellToMonster(@PathVariable("monsterId") Long monsterId,
                                                                   @PathVariable("spellId") Long spellId) {
        return new ResponseEntity<>(monsterService.addSpellToMonster(monsterId, spellId), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMonsterImage(@PathVariable Long id) {
        byte[] image = monsterService.getImageById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
