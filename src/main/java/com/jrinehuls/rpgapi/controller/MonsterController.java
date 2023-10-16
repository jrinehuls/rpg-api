package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.dto.MonsterDto;
import com.jrinehuls.rpgapi.model.Monster;
import com.jrinehuls.rpgapi.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/monster")
public class MonsterController {

    @Autowired
    MonsterService monsterService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Monster> saveMonster(@ModelAttribute MonsterDto monsterDto) {
        return new ResponseEntity<>(monsterService.saveMonster(monsterDto), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public String sayHi() {
        return "Hello";
    }

}
