package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.exception.ErrorResponse;
import com.jrinehuls.rpgapi.service.MonsterService;
import com.jrinehuls.rpgapi.validation.groups.MonsterCreation;
import com.jrinehuls.rpgapi.validation.groups.MonsterUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    // Post Monster
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of monster",
                    content = @Content(schema = @Schema(implementation = MonsterResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: monster name already in use",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create monster", description = "Creates a new monster based on provided form data")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> saveMonster(@Validated({MonsterCreation.class})
            @ModelAttribute MonsterRequestDto monsterRequestDto) {
        return new ResponseEntity<>(monsterService.saveMonster(monsterRequestDto), HttpStatus.CREATED);
    }

    // Get Monster by ID
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of monster",
                    content = @Content(schema = @Schema(implementation = MonsterResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data type on id"),
            @ApiResponse(responseCode = "404", description = "Monster doesn't exist",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Get single monster", description = "Retrieves a monster based on id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> getMonster(@PathVariable("id") Long id) {
        return new ResponseEntity<>(monsterService.getMonster(id), HttpStatus.OK);
    }

    // Get All Monsters
    @ApiResponse(responseCode = "200", description = "Successful retrieval of monsters",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MonsterResponseDto.class)))
    )
    @Operation(summary = "Get all monsters", description = "Returns all saved monsters")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MonsterResponseDto>> getAllMonsters() {
        return new ResponseEntity<>(monsterService.getAllMonsters(), HttpStatus.OK);
    }

    // Update Monster
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update monster",
                    content = @Content(schema = @Schema(implementation = MonsterResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid payload or id type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Monster not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: monster name already in use",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Update monster", description = "Update a monster with the new form data")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> updateMonster(@PathVariable("id") Long id,
            @Validated({MonsterUpdate.class}) @ModelAttribute MonsterRequestDto monsterRequestDto) {
        return new ResponseEntity<>(monsterService.updateMonster(id, monsterRequestDto), HttpStatus.OK);
    }

    // Delete Monster
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful deletion of monster"),
            @ApiResponse(responseCode = "400", description = "Invalid id data type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Monster not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Delete monster", description = "Deletes a monster based on id")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMonster(@PathVariable("id") Long id) {
        monsterService.deleteMonster(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get a Monster's Spells
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of monster's spells",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SpellResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid id data type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Monster not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Get a monster's spells", description = "Gets the spells a monster knows based on monster id")
    @GetMapping("/{id}/spells")
    public ResponseEntity<Set<SpellResponseDto>> getSpells(@PathVariable("id") Long id) {
        return new ResponseEntity<>(monsterService.getSpells(id), HttpStatus.OK);
    }

    // Teach Monster Spell
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully teach monster a spell",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SpellResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid id data type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Monster or spell not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Teach monster a spell", description = "Teach the spell to a monster based on spell id and monster id")
    @PatchMapping("/{monsterId}/add-spell/{spellId}")
    public ResponseEntity<Set<SpellResponseDto>> addSpellToMonster(@PathVariable("monsterId") Long monsterId,
                                                                   @PathVariable("spellId") Long spellId) {
        return new ResponseEntity<>(monsterService.addSpellToMonster(monsterId, spellId), HttpStatus.OK);
    }

    // Forget Spell for Monster
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully forgot a spell for the monster",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SpellResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid id data type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Monster or spell not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Forget spell for a monster", description = "Forget the spell of a monster based on spell id and monster id")
    @PatchMapping("/{monsterId}/remove-spell/{spellId}")
    public ResponseEntity<Set<SpellResponseDto>> removeSpellFromMonster(@PathVariable("monsterId") Long monsterId,
                                                                   @PathVariable("spellId") Long spellId) {
        return new ResponseEntity<>(monsterService.removeSpellFromMonster(monsterId, spellId), HttpStatus.OK);
    }

    // Get Monster Image
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got a monster's image"),
            @ApiResponse(responseCode = "400", description = "Invalid id data type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Monster not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Get a monster image", description = "Get's the monster's image based on monster id")
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMonsterImage(@PathVariable Long id) {
        byte[] image = monsterService.getImageById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
