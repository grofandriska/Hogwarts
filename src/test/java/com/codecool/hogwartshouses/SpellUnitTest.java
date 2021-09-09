package com.codecool.hogwartshouses;

import com.codecool.hogwartshouses.controller.SpellController;
import com.codecool.hogwartshouses.exception.SpellNotFoundException;
import com.codecool.hogwartshouses.model.DTO.SpellDTO;
import com.codecool.hogwartshouses.model.DTO.TeacherDTO;
import com.codecool.hogwartshouses.model.entity.Spell;
import com.codecool.hogwartshouses.model.entity.Wand;
import com.codecool.hogwartshouses.model.entity.types.ColorType;
import com.codecool.hogwartshouses.model.entity.types.SpellType;
import com.codecool.hogwartshouses.model.entity.types.WoodType;
import com.codecool.hogwartshouses.service.BuildingService;
import com.codecool.hogwartshouses.service.SpellService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.*;
import java.util.stream.Collectors;

@WebMvcTest(SpellController.class)
public class SpellUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpellService service;

    @MockBean
    private BuildingService buildingService;

    @Test
    public void findAll() throws Exception {

        //spellOne
        Spell spell = new Spell();
        spell.setId(1L);
        spell.setBanned(true);
        spell.setName(SpellType.ABRAKADABRA);
        spell.setPower(6);

        //spellTwo
        Spell spellTwo = new Spell();
        spellTwo.setId(2L);
        spellTwo.setBanned(false);
        spellTwo.setName(SpellType.FLY);
        spellTwo.setPower(8);

        when(service.findAll()).thenReturn(new HashSet<>(Set.of(spell, spellTwo)) {
        });

        mockMvc.perform(get("/spells/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(spell.getId().intValue())))
                .andExpect(jsonPath("$[0].banned", is(spell.isBanned())))
                .andExpect(jsonPath("$[0].name", is(spell.getName().toString())))
                .andExpect(jsonPath("$[0].power", is(spell.getPower())))
                // .andExpect(jsonPath("$[0].wands", is(spell.getWands().toString())))

                .andExpect(jsonPath("$[1].id", is(spellTwo.getId().intValue())))
                .andExpect(jsonPath("$[1].banned", is(spellTwo.isBanned())))
                .andExpect(jsonPath("$[1].name", is(spellTwo.getName().toString())))
                .andExpect(jsonPath("$[1].power", is(spellTwo.getPower())));
              //.andExpect(jsonPath("$[1].wands", is(spell.getWands().toString())));

    }

    @Test
    public void findSpellByIdThrowsExceptionAndUrlIsOk() throws Exception {
        //create Spell to test
        Spell spell = new Spell();
        spell.setBanned(false);
        spell.setId(2L);
        spell.setName(SpellType.HOCUS_POK);
        spell.setPower(8);

        when(service.findById(spell.getId())).thenThrow(SpellNotFoundException.class);
        mockMvc.perform(get("/spell/1")).andExpect(status().is(404));

        ModelMapper modelMapper = new ModelMapper();
        SpellDTO spellDTO = modelMapper.map(spell, SpellDTO.class);
        when(service.findById(1L)).thenReturn(spellDTO);
        mockMvc.perform(get("/spells/1")).andExpect(status().isOk());
    }

}
