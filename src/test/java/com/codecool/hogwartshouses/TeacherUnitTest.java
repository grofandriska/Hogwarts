package com.codecool.hogwartshouses;


import com.codecool.hogwartshouses.controller.TeacherController;
import com.codecool.hogwartshouses.exception.TeacherNotFoundException;
import com.codecool.hogwartshouses.model.DTO.TeacherDTO;
import com.codecool.hogwartshouses.model.entity.Teacher;
import com.codecool.hogwartshouses.model.entity.Wand;
import com.codecool.hogwartshouses.model.entity.types.ColorType;
import com.codecool.hogwartshouses.model.entity.types.SubjectType;
import com.codecool.hogwartshouses.model.entity.types.WoodType;
import com.codecool.hogwartshouses.service.BuildingService;
import com.codecool.hogwartshouses.service.TeacherService;
import org.junit.jupiter.api.Test;
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

import java.util.Set;


@WebMvcTest(TeacherController.class)
public class TeacherUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private BuildingService buildingService;


    @Test
    public void getAllTeacher() throws Exception {
        Wand wand = new Wand();
        wand.setId(1L);
        wand.setWoodType(WoodType.BUTTERNUT);
        wand.setColorType(ColorType.GREY);

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("John Smith");
        teacher.setAge(25);
        teacher.setSubject(SubjectType.HISTORY);
        teacher.setWitch(false);
        teacher.setWand(wand);

        when(teacherService.findAll()).thenReturn(Set.of(teacher));

        mockMvc.perform(get("/teachers/all")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(teacher.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(teacher.getName())))
                .andExpect(jsonPath("$[0].age", is(teacher.getAge())))
                .andExpect(jsonPath("$[0].subject", is(teacher.getSubject().toString())))
                .andExpect(jsonPath("$[0].witch", is(teacher.getWitch())));
    }

    @Test
    public void findTeacherById_ReturnTeacherDTO() throws Exception {
        Wand wand = new Wand();
        wand.setId(1L);
        wand.setWoodType(WoodType.BUTTERNUT);
        wand.setColorType(ColorType.GREY);

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("John Smith");
        teacher.setAge(25);
        teacher.setSubject(SubjectType.HISTORY);
        teacher.setWitch(false);
        teacher.setWand(wand);

        ModelMapper modelMapper = new ModelMapper();

        TeacherDTO teacherDTO = modelMapper.map(teacher, TeacherDTO.class);

        when(teacherService.findById(1L)).thenReturn(teacherDTO);

        mockMvc.perform(get("/teachers/1/")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(teacherDTO.getId().intValue())))
                .andExpect(jsonPath("$.name", is(teacherDTO.getName())))
                .andExpect(jsonPath("$.age", is(teacherDTO.getAge())))
                .andExpect(jsonPath("$.subject", is(teacherDTO.getSubject().toString())))
                .andExpect(jsonPath("$.witch", is(teacherDTO.getWitch())));
    }

    @Test
    public void findTeacherById_ThrowException() throws Exception {

        when(teacherService.findById(2L)).thenThrow(TeacherNotFoundException.class);

        mockMvc.perform(get("/teachers/2/")).andExpect(status().isBadRequest());
    }
}
