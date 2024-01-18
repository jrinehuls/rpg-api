package com.jrinehuls.rpgapi.util.mapper;

import com.jrinehuls.rpgapi.dto.monster.MonsterRequestDto;
import com.jrinehuls.rpgapi.dto.monster.MonsterResponseDto;
import com.jrinehuls.rpgapi.entity.Monster;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@NoArgsConstructor
public class MonsterMapper {

    public Monster mapDtoToMonster(MonsterRequestDto monsterDto) {
        Monster monster = new Monster();
        monster.setName(monsterDto.getName());
        monster.setHp(monsterDto.getHp());
        monster.setMp(monsterDto.getMp());
        monster.setAttack(monsterDto.getAttack());
        monster.setDefense(monsterDto.getDefense());
        monster.setMagicAttack(monsterDto.getMagicAttack());
        monster.setMagicDefense(monsterDto.getMagicDefense());
        monster.setSpeed(monsterDto.getSpeed());
        monster.setBaseGold(monsterDto.getBaseGold());
        monster.setBaseExp(monsterDto.getBaseExp());
        monster.setImage(MonsterMapper.convertImageToBytes(monsterDto.getImage()));

        return monster;
    }

    public MonsterResponseDto mapMonsterToDto(Monster monster) {
        MonsterResponseDto monsterDto = new MonsterResponseDto();
        monsterDto.setId(monster.getId());
        monsterDto.setName(monster.getName());
        monsterDto.setHp(monster.getHp());
        monsterDto.setMp(monster.getMp());
        monsterDto.setAttack(monster.getAttack());
        monsterDto.setDefense(monster.getDefense());
        monsterDto.setMagicAttack(monster.getMagicAttack());
        monsterDto.setMagicDefense(monster.getMagicDefense());
        monsterDto.setSpeed(monster.getSpeed());
        monsterDto.setBaseGold(monster.getBaseGold());
        monsterDto.setBaseExp(monster.getBaseExp());
        monsterDto.setImage(monster.getImage());

        return monsterDto;
    }

    private static byte[] convertImageToBytes(MultipartFile image) {
        String filename = image.getOriginalFilename();
        String contentType = image.getContentType();
        System.out.println(filename + " " + contentType);
        try {
            System.out.println("I got the bytes");
            return image.getBytes();
        } catch (IOException e) {
            return null;
            //throw new RuntimeException(e);
        }
    }

}
