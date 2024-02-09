package com.jrinehuls.rpgapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "description", length = 150, nullable = false)
    private String description;

    @Column (name = "power", nullable = false)
    private Integer power;

    @ManyToMany
    @JoinTable(
            name = "monster_spell",
            joinColumns = @JoinColumn(name = "spell_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "monster_id", referencedColumnName = "id")
    )
    private Set<Monster> monsters;

}
