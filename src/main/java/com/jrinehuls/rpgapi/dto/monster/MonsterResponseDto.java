package com.jrinehuls.rpgapi.dto.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = { "id", "name", "hp", "mp", "attack", "defense", "magicAttack", "magicDefense", "speed",
        "baseGold", "baseExp", "image"})
public class MonsterResponseDto {

    private Long id;

    private String name;

    private Integer hp;

    private Integer mp;

    private Integer attack;

    private Integer defense;

    private Integer magicAttack;

    private Integer magicDefense;

    private Integer speed;

    private Integer baseGold;

    private Integer baseExp;

    private byte[] image;

    private String imageExtension;

}
