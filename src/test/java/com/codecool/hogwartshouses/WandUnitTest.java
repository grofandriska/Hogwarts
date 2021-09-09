package com.codecool.hogwartshouses;

import com.codecool.hogwartshouses.controller.WandController;
import com.codecool.hogwartshouses.model.DTO.WandDTO;
import com.codecool.hogwartshouses.model.entity.Spell;
import com.codecool.hogwartshouses.model.entity.Wand;
import com.codecool.hogwartshouses.model.entity.types.ColorType;
import com.codecool.hogwartshouses.model.entity.types.WoodType;
import com.codecool.hogwartshouses.service.BuildingService;
import com.codecool.hogwartshouses.service.WandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

@WebMvcTest(WandController.class)
public class WandUnitTest {

    private Wand testWand;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WandService wandService;

    @MockBean
    private BuildingService buildingService;

    @BeforeEach
    private void init() {
        Wand wand = new Wand();
        wand.setId(1L);
        wand.setWoodType(WoodType.BLACK_CHERRY);
        wand.setColorType(ColorType.BLACK);
        wand.setSpells(new HashSet<>());

        this.testWand = wand;
    }

    @Test
    void getWands_requestForExistingWandList_returnsListOfWands() throws Exception {
        when(wandService.findAll()).thenReturn(Set.of(testWand));

        mockMvc.perform(get("/wands/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(testWand.getId().intValue())))
                .andExpect(jsonPath("$[0].woodType", is(testWand.getWoodType().name())))
                .andExpect(jsonPath("$[0].colorType", is(testWand.getColorType().name())));
    }

    @Test
    void getWands_requestedWithoutWand_returnsEmptyListOfWands() throws Exception {
        when(wandService.findAll()).thenReturn(Set.of());

        mockMvc.perform(get("/wands/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getWandById_requestForExistingWand_returnsListOfWands() throws Exception {

        ModelMapper modelMapper = new ModelMapper();

        WandDTO wandDTO = modelMapper.map(testWand, WandDTO.class);

        when(wandService.findById(1L)).thenReturn(wandDTO);

        mockMvc.perform(get("/wands/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(wandDTO.getId().intValue())))
                .andExpect(jsonPath("$.woodType", is(wandDTO.getWoodType().name())))
                .andExpect(jsonPath("$.colorType", is(wandDTO.getColorType().name())));
    }
}
