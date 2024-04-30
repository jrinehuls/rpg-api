package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.dto.spell.SpellRequestDto;
import com.jrinehuls.rpgapi.dto.spell.SpellResponseDto;
import com.jrinehuls.rpgapi.exception.ErrorResponse;
import com.jrinehuls.rpgapi.service.SpellService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Spell Controller", description = "Endpoints for managing spells")
public class SpellController {

    private final SpellService spellService;

    // Post Spell
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of spell",
                    content = @Content(schema = @Schema(implementation = SpellResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid payload",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: spell name already in use",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Create spell", description = "Creates a new spell based on provided payload")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpellResponseDto> createSpell(@Valid @RequestBody SpellRequestDto dto) {
        return new ResponseEntity<>(spellService.saveSpell(dto), HttpStatus.CREATED);
    }

    // Get Spell by ID
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved spell",
                    content = @Content(schema = @Schema(implementation = SpellResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data type for id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Spell with given id does not exist",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Get spell by ID", description = "Retrieves the spell with the given ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpellResponseDto> getSpell(@PathVariable("id") Long id) {
        return new ResponseEntity<>(spellService.getSpell(id), HttpStatus.OK);
    }

    // Get All Spells
    @ApiResponse(responseCode = "200", description = "Successfully retrieved spells",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = SpellResponseDto.class)))
    )
    @Operation(summary = "Get all spells", description = "Retrieves all the spells")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SpellResponseDto>> getAllSpells() {
        return new ResponseEntity<>(spellService.getAllSpells(), HttpStatus.OK);
    }

    // Update Spell
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updates spell",
                    content = @Content(schema = @Schema(implementation = SpellResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid payload or id type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Spell not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict: spell name already in use",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Update spell", description = "Update a spell with the new data")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpellResponseDto> updateSpell(@PathVariable("id") Long id, @Valid @RequestBody SpellRequestDto dto) {
        return new ResponseEntity<>(spellService.updateSpell(id, dto), HttpStatus.CREATED);
    }

    // Delete Spell
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful deletion of spell"),
            @ApiResponse(responseCode = "400", description = "Invalid id data type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Spell not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Delete spell", description = "Deletes a spell based on id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> updateSpell(@PathVariable("id") Long id) {
        spellService.deleteSpell(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get Monsters That Know a Given Spell
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of monsters that know this spell",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MonsterResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid id data type supplied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Spell not found with given id",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "Get a monsters that know a given spell", description = "Gets the monsters that know a spell based on spell id")
    @GetMapping(value = "/{id}/monsters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<MonsterResponseDto>> getMonsters(@PathVariable("id") Long id) {
        return new ResponseEntity<>(spellService.getMonsters(id), HttpStatus.OK);
    }

}
