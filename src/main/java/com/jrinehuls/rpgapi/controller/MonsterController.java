package com.jrinehuls.rpgapi.controller;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.jrinehuls.rpgapi.dto.MonsterDto;
import com.jrinehuls.rpgapi.model.Monster;
import com.jrinehuls.rpgapi.service.MonsterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Validated
@RestController
@RequestMapping("api/monster")
public class MonsterController {

    @Autowired
    MonsterService monsterService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterDto> saveMonster(@Valid @ModelAttribute MonsterDto monsterDto) {
        // return new ResponseEntity<>(monsterService.saveMonster(monsterDto), HttpStatus.CREATED);

        // Ignore InputStream on file
        return new ResponseEntity<>(monsterDto, HttpStatus.CREATED);
    }


}
