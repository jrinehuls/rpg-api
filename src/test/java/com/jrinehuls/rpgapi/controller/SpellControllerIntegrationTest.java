package com.jrinehuls.rpgapi.controller;

import com.jrinehuls.rpgapi.entity.Spell;
import com.jrinehuls.rpgapi.repository.SpellRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SpellControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SpellRepository spellRepository;

    List<Spell> spells = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Spell spell = new Spell();
        spell.setId(1L);
        spell.setName("Flare");
        spell.setDescription("A fire type attack");
        spell.setPower(10);
        spells.add(spell);
    }

    @AfterEach
    void tearDown() {
        spells.clear();
    }

    @Test
    void contestLoads(){
        assertNotNull(mockMvc);
    }

    @Test
    void getAllSpellsTest() throws Exception {
        when(spellRepository.findAll()).thenReturn(spells);
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/spell");
        mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[?(@.id == 1 && @.name == \"Flare\")]").exists());
    }
}
