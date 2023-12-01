package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.model.Monster;
import com.jrinehuls.rpgapi.service.MonsterService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/monster")
public class MonsterController {

    @Autowired
    MonsterService monsterService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> saveMonster(@Valid @ModelAttribute MonsterRequestDto monsterRequestDto) {
        Monster monster = monsterService.saveMonster(monsterRequestDto);
        MonsterResponseDto monsterResponseDto = modelMapper.map(monster, MonsterResponseDto.class);
        return new ResponseEntity<>(monsterResponseDto, HttpStatus.CREATED);
    }


}
