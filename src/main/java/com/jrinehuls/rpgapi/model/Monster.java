package com.jrinehuls.rpgapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "monster")
@Getter
@Setter
@NoArgsConstructor
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private Integer HP;
    private Integer MP;
    private Integer attack;
    private Integer defense;
    @JsonProperty(value = "magic_attack")
    private Integer magicAttack;
    private Integer magicDefense;
    private Integer speed;
    private Integer baseGold;
    private Integer baseExp;


    @Lob
    @Column(name = "image", length = 65535)
    private byte[] image;


}
