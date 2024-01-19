package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.entity.Monster;
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

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> saveMonster(@Valid @ModelAttribute MonsterRequestDto monsterRequestDto) {
        return new ResponseEntity<>(monsterService.saveMonster(monsterRequestDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> getMonster(@PathVariable("id") Long id) {
        return new ResponseEntity<>(monsterService.getMonster(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonsterResponseDto> updateMonster(@PathVariable("id") Long id, @Valid @ModelAttribute
                                                            MonsterRequestDto monsterRequestDto) {
        return new ResponseEntity<>(monsterService.updateMonster(id, monsterRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMonster(@PathVariable("id") Long id) {
        monsterService.deleteMonster(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMonsterImage(@PathVariable Long id) {
        byte[] image = monsterService.getImageById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
