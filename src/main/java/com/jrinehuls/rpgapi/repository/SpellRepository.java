package com.jrinehuls.rpgapi.repository;

import com.jrinehuls.rpgapi.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpellRepository extends JpaRepository<Spell, Long> {
    List<Spell> findByMonsterId(Long id);
}
