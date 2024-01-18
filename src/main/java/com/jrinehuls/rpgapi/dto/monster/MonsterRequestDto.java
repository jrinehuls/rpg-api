package com.jrinehuls.rpgapi.dto.monster;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonsterRequestDto {

    @NotNull(message = "name must be provided")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotNull(message = "hp must be provided")
    private Integer hp;

    @NotNull(message = "mp must be provided")
    private Integer mp;

    @NotNull(message = "attack must be provided")
    private Integer attack;

    @NotNull(message = "defense must be provided")
    private Integer defense;

    @NotNull(message = "magicAttack must be provided")
    private Integer magicAttack;

    @NotNull(message = "magicDefense must be provided")
    private Integer magicDefense;

    @NotNull(message = "speed must be provided")
    private Integer speed;

    @NotNull(message = "baseGold must be provided")
    private Integer baseGold;

    @NotNull(message = "baseExp must be provided")
    private Integer baseExp;

    @NotNull(message = "image must be provided")
    // Can't set to not blank, but converts bytes to empty string if image field created with no attachment in Postman
    // Need to create custom validators for content type and blank file name
    private MultipartFile image;

}
