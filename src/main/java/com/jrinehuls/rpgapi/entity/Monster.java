package com.jrinehuls.rpgapi.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "monster")
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder(value = {})
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "hp", nullable = false)
    private Integer hp;

    @Column(name = "mp", nullable = false)
    private Integer mp;

    @Column(name = "attack", nullable = false)
    private Integer attack;

    @Column(name = "defense", nullable = false)
    private Integer defense;

    @Column(name = "magic_attack", nullable = false)
    private Integer magicAttack;

    @Column(name = "magic_defense", nullable = false)
    private Integer magicDefense;

    @Column(name = "speed", nullable = false)
    private Integer speed;

    @Column(name = "base_gold", nullable = false)
    private Integer baseGold;

    @Column(name = "base_exp", nullable = false)
    private Integer baseExp;

    @Lob
    @Column(name = "image", nullable = false)
    // If coming from form, sends empty string
    private byte[] image;

}
