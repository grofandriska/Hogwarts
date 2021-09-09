package com.codecool.hogwartshouses.dao;

import com.codecool.hogwartshouses.model.entity.Student;
import com.codecool.hogwartshouses.model.entity.types.PetType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentDAO {
    void save(Student student);

    void deleteById(Long id);

    List<Student> getAll();

    Optional<Student> getById(Long id);

    List<Student> getAllByRoomId(Long id);

}
