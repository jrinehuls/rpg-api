package com.jrinehuls.rpgapi.dto.spell;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder(value = { "id", "name", "description", "power" })
public class SpellResponseDto {

    private Long id;
    private String name;
    private String description;
    private Integer power;

}
