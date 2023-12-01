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
@JsonPropertyOrder()
public class MonsterResponseDto {

    private Long id;

    private String name;

    private Integer hp;

    private Integer mp;

    private Integer attack;

    private Integer defense;

    @JsonProperty(value="magic_attack")
    private Integer magicAttack;

    @JsonProperty(value="magic_defense")
    private Integer magicDefense;

    private Integer speed;

    @JsonProperty(value="base_gold")
    private Integer baseGold;

    @JsonProperty(value="base_exp")
    private Integer baseExp;

    private byte[] image;

}
