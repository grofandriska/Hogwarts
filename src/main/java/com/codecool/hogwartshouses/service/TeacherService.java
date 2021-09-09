package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.model.DTO.TeacherDTO;
import com.codecool.hogwartshouses.model.DTO.WandDTO;
import com.codecool.hogwartshouses.model.entity.Teacher;
import com.codecool.hogwartshouses.model.entity.Wand;
import com.codecool.hogwartshouses.model.entity.types.ColorType;

import java.util.List;
import java.util.Set;

public interface TeacherService {
    Set<Teacher> findAll();

    TeacherDTO findById(Long id);

    List<String> findAllByWoodType(String woodType);

    List<String> findAllByColor(ColorType colorType);
}
