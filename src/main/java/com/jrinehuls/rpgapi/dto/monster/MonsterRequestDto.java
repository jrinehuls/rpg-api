package com.jrinehuls.rpgapi.dto.monster;


import com.jrinehuls.rpgapi.validation.Image;
import com.jrinehuls.rpgapi.validation.MonsterCreation;
import com.jrinehuls.rpgapi.validation.MonsterUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonsterRequestDto {

    @NotNull(message = "name must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    @NotBlank(message = "name cannot be blank", groups = {MonsterCreation.class, MonsterUpdate.class})
    @Length(max = 255, message = "name cannot be longer than 255 characters", groups = {MonsterCreation.class, MonsterUpdate.class})
    private String name;

    @NotNull(message = "hp must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer hp;

    @NotNull(message = "mp must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer mp;

    @NotNull(message = "attack must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer attack;

    @NotNull(message = "defense must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer defense;

    @NotNull(message = "magicAttack must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer magicAttack;

    @NotNull(message = "magicDefense must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer magicDefense;

    @NotNull(message = "speed must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer speed;

    @NotNull(message = "baseGold must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer baseGold;

    @NotNull(message = "baseExp must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer baseExp;

    @NotNull(message = "image must be provided", groups = {MonsterCreation.class})
    @Image(groups = {MonsterCreation.class})
    private MultipartFile image;

}
