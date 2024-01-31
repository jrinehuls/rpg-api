package com.jrinehuls.rpgapi.repository;

import com.jrinehuls.rpgapi.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MonsterRepository extends JpaRepository<Monster, Long> {

}
