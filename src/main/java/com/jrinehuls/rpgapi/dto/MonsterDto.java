package com.jrinehuls.rpgapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonsterDto {

    @NotNull(message = "name must be provided")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotNull(message = "hp must be provided")
    @NotBlank(message = "hp cannot be blank")
    private Integer HP;

    @NotNull(message = "mp must be provided")
    @NotBlank(message = "mp cannot be blank")
    private Integer MP;

    @NotNull(message = "attack must be provided")
    @NotBlank(message = "attack cannot be blank")
    private Integer attack;

    @NotNull(message = "defense must be provided")
    @NotBlank(message = "defense cannot be blank")
    private Integer defense;

    @NotNull(message = "magic_attack must be provided")
    @NotBlank(message = "magic_attack cannot be blank")
    @JsonProperty(value = "magic_attack")
    private Integer magicAttack;

    @NotNull(message = "magic_defense must be provided")
    @NotBlank(message = "magic_defense cannot be blank")
    @JsonProperty(value = "magic_defense")
    private Integer magicDefense;

    @NotNull(message = "speed must be provided")
    @NotBlank(message = "speed cannot be blank")
    private Integer speed;

    @NotNull(message = "base_gold must be provided")
    @NotBlank(message = "base_gold cannot be blank")
    @JsonProperty(value = "base_gold")
    private Integer baseGold;

    @NotNull(message = "base_exp must be provided")
    @NotBlank(message = "base_exp cannot be blank")
    @JsonProperty(value = "base_exp")
    private Integer baseExp;

    private MultipartFile image;

}
