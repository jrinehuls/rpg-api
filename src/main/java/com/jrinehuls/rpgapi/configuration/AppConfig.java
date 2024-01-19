package com.jrinehuls.rpgapi.configuration;

import com.jrinehuls.rpgapi.util.mapper.MonsterMapper;
import com.jrinehuls.rpgapi.util.mapper.SpellMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MonsterMapper getMonsterMapper() {
        return new MonsterMapper();
    }

    @Bean
    public SpellMapper getSpellMapper() {
        return new SpellMapper();
    }

}
