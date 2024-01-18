package com.jrinehuls.rpgapi.configuration;

import com.jrinehuls.rpgapi.util.mapper.MonsterMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MonsterMapper getMonsterMapper() {
        return new MonsterMapper();
    }

}
