package com.codecool.hogwartshouses.repositories;

import com.codecool.hogwartshouses.model.DTO.WandDTO;
import com.codecool.hogwartshouses.model.entity.Teacher;
import com.codecool.hogwartshouses.model.entity.Wand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findAll();
}
