package com.jrinehuls.rpgapi.configuration;

import com.jrinehuls.rpgapi.util.mapper.MonsterMapper;
import com.jrinehuls.rpgapi.util.mapper.SpellMapper;
import com.jrinehuls.rpgapi.util.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

    @Bean
    public MonsterMapper getMonsterMapper() {
        return new MonsterMapper();
    }

    @Bean
    public SpellMapper getSpellMapper() {
        return new SpellMapper();
    }

    @Bean
    public UserMapper getUserMapper() {
        return new UserMapper();
    }

}
