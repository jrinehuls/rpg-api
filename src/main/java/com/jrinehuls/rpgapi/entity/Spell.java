package com.jrinehuls.rpgapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "spell", uniqueConstraints = {
        @UniqueConstraint(name = "UC_spell_name", columnNames = { "name" })
})
@Getter
@Setter
@NoArgsConstructor
public class Spell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column (name = "power", nullable = false)
    private Integer power;

}
