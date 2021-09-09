package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.model.DTO.TeacherDTO;
import com.codecool.hogwartshouses.model.entity.Teacher;
import com.codecool.hogwartshouses.model.entity.types.ColorType;
import com.codecool.hogwartshouses.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    @ResponseBody
    public Set<Teacher> getTeachers() {
        return teacherService.findAll();
    }

    @GetMapping("/{id}/")
    @ResponseBody
    public TeacherDTO getTeacher(@PathVariable("id") Long id) {
        return teacherService.findById(id);
    }

    @GetMapping
    @ResponseBody
    public List<String> getTeachersByWoodType(@RequestParam("woodType") String woodType) {
        return teacherService.findAllByWoodType(woodType);
    }

    @GetMapping("/wand")
    @ResponseBody
    public List<String> getTeachersByColorType(@RequestParam("colorType") String color) {
        return teacherService.findAllByColor(ColorType.valueOf(color));
    }

}
