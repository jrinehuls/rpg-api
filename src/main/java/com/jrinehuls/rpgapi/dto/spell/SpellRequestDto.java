package com.jrinehuls.rpgapi.dto.spell;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class SpellRequestDto {

    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    @Length(max = 25, message = "name cannot be longer than 25 characters")
    private String name;

    @NotBlank(message = "description cannot be blank")
    @NotNull(message = "description cannot be null")
    @Length(max = 255, message = "description cannot be longer than 255 characters")
    private String description;

    @NotNull(message = "power cannot be null")
    @Min(value = 0, message = "power cannot be negative")
    private Integer power;

}
