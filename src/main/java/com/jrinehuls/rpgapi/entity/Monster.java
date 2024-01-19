package com.jrinehuls.rpgapi.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "monster", uniqueConstraints = {
        @UniqueConstraint(name = "UC_name", columnNames = { "name" } )
})
@Getter
@Setter
@NoArgsConstructor
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
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
    private byte[] image;

    @Column(name = "image_extension", nullable = false)
    private String imageExtension;

}
