package com.jrinehuls.rpgapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "monster")
@Getter
@Setter
@NoArgsConstructor
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "hp", nullable = false)
    private Integer HP;

    @Column(name = "mp", nullable = false)
    private Integer MP;

    @Column(name = "attack", nullable = false)
    private Integer attack;

    @Column(name = "defense", nullable = false)
    private Integer defense;

    @Column(name = "magic_attack", nullable = false)
    @JsonProperty(value = "magic_attack")
    private Integer magicAttack;

    @Column(name = "magic_defense", nullable = false)
    @JsonProperty(value = "magic_defense")
    private Integer magicDefense;

    @Column(name = "speed", nullable = false)
    private Integer speed;

    @Column(name = "base_gold", nullable = false)
    @JsonProperty(value = "base_gold")
    private Integer baseGold;

    @Column(name = "base_exp", nullable = false)
    @JsonProperty(value = "base_exp")
    private Integer baseExp;

    @Lob
    @Column(name = "image", length = 65535)
    private byte[] image;


}