package com.jrinehuls.rpgapi.dto.monster;


import com.jrinehuls.rpgapi.validation.annotations.Image;
import com.jrinehuls.rpgapi.validation.groups.MonsterCreation;
import com.jrinehuls.rpgapi.validation.groups.MonsterUpdate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonsterRequestDto {

    @NotNull(message = "name must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    @NotBlank(message = "name cannot be blank", groups = {MonsterCreation.class, MonsterUpdate.class})
    @Length(max = 25, message = "name cannot be longer than 25 characters",
            groups = {MonsterCreation.class, MonsterUpdate.class})
    private String name;

    @NotNull(message = "hp must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    @Range(min = 1, max = 255, message = "hp must be between 1 and 255",
            groups = {MonsterCreation.class, MonsterUpdate.class})
    private Integer hp;

    @NotNull(message = "mp must be provided", groups = {MonsterCreation.class, MonsterUpdate.class})
    @Range(min = 1, max = 255, message = "mp must be between 1 and 255",
            groups = {MonsterCreation.class, MonsterUpdate.class})
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
