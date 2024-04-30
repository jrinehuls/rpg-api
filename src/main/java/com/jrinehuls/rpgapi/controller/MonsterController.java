package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.service.MonsterService;
import com.jrinehuls.rpgapi.validation.groups.MonsterCreation;
import com.jrinehuls.rpgapi.validation.groups.MonsterUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Monster Controller", description = "Endpoints for managing monsters")
public class MonsterController {

    private final MonsterService monsterService;

    @Operation(summary = "Create monster", description = "Creates a new monster based on provided form data")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> saveMonster(@Validated({MonsterCreation.class})
            @ModelAttribute MonsterRequestDto monsterRequestDto) {
        return new ResponseEntity<>(monsterService.saveMonster(monsterRequestDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get single monster", description = "Retrieves a monster based on id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> getMonster(@PathVariable("id") Long id) {
        return new ResponseEntity<>(monsterService.getMonster(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all monsters", description = "Returns all saved monsters")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MonsterResponseDto>> getAllMonsters() {
        return new ResponseEntity<>(monsterService.getAllMonsters(), HttpStatus.OK);
    }

    @Operation(summary = "Update monster", description = "Update a monster with the new form data")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> updateMonster(@PathVariable("id") Long id,
            @Validated({MonsterUpdate.class}) @ModelAttribute MonsterRequestDto monsterRequestDto) {
        return new ResponseEntity<>(monsterService.updateMonster(id, monsterRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete monster", description = "Deletes a monster based on id")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMonster(@PathVariable("id") Long id) {
        monsterService.deleteMonster(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get a monster's spells", description = "Gets the spells a monster knows based on monster id")
    @GetMapping("/{id}/spells")
    public ResponseEntity<Set<SpellResponseDto>> getSpells(@PathVariable("id") Long id) {
        return new ResponseEntity<>(monsterService.getSpells(id), HttpStatus.OK);
    }

    @Operation(summary = "Teach monster a spell", description = "Teach the spell to a monster based on spell id and monster id")
    @PatchMapping("/{monsterId}/add-spell/{spellId}")
    public ResponseEntity<Set<SpellResponseDto>> addSpellToMonster(@PathVariable("monsterId") Long monsterId,
                                                                   @PathVariable("spellId") Long spellId) {
        return new ResponseEntity<>(monsterService.addSpellToMonster(monsterId, spellId), HttpStatus.OK);
    }

    @Operation(summary = "Forget spell for a monster", description = "Forget the spell of a monster based on spell id and monster id")
    @PatchMapping("/{monsterId}/remove-spell/{spellId}")
    public ResponseEntity<Set<SpellResponseDto>> removeSpellFromMonster(@PathVariable("monsterId") Long monsterId,
                                                                   @PathVariable("spellId") Long spellId) {
        return new ResponseEntity<>(monsterService.removeSpellFromMonster(monsterId, spellId), HttpStatus.OK);
    }

    @Operation(summary = "Get a monster image", description = "Get's the monster's image based on monster id")
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMonsterImage(@PathVariable Long id) {
        byte[] image = monsterService.getImageById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
