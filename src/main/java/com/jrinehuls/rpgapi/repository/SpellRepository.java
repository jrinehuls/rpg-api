package com.jrinehuls.rpgapi.repository;

import com.jrinehuls.rpgapi.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellRepository extends JpaRepository<Spell, Long> {

}
