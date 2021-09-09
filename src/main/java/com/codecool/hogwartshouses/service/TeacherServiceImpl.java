package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.exception.TeacherNotFoundException;
import com.codecool.hogwartshouses.model.DTO.TeacherDTO;
import com.codecool.hogwartshouses.model.DTO.WandDTO;
import com.codecool.hogwartshouses.model.entity.Teacher;
import com.codecool.hogwartshouses.model.entity.Wand;
import com.codecool.hogwartshouses.model.entity.types.ColorType;
import com.codecool.hogwartshouses.model.entity.types.WoodType;
import com.codecool.hogwartshouses.repositories.TeacherRepository;
import com.codecool.hogwartshouses.repositories.WandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    private final WandRepository wandRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, ModelMapper modelMapper, WandRepository wandRepository) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
        this.wandRepository = wandRepository;
    }

    @Override
    public Set<Teacher> findAll() {
        HashSet<Teacher> teachers = new HashSet<>();
        teacherRepository.findAll().iterator().forEachRemaining(teachers::add);
        return teachers;
    }

    @Override
    public TeacherDTO findById(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            return modelMapper.map(optionalTeacher.get(), TeacherDTO.class);
        }
        throw new TeacherNotFoundException("Teacher not exists with this id " + id);
    }

    @Override
    public List<String> findAllByWoodType(String woodType) {
        List<Long> ids = wandRepository.findAll().stream()
                .filter(wand -> wand.getWoodType().equals(WoodType.valueOf(woodType)))
                .map(Wand::getId)
                .collect(Collectors.toList());
        return teacherRepository.findAll().stream()
                .filter(teacher -> ids.contains(teacher.getWand().getId()))
                .map(Teacher::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByColor(ColorType colorType) {
        return teacherRepository.findAll()
                .stream()
                .filter(teacher -> teacher.getWand().getColorType().equals(colorType))
                .map(Teacher::getName).collect(Collectors.toList());
    }
}
