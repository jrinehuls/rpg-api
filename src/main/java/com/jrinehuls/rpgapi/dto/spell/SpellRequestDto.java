package com.jrinehuls.rpgapi.dto.spell;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SpellRequestDto {

    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    private String name;

    @NotBlank(message = "description cannot be blank")
    @NotNull(message = "description cannot be null")
    private String description;

    @NotNull(message = "power cannot be null")
    @Min(value = 0, message = "power cannot be negative")
    private Integer power;

}
