package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.dao.StudentDAO;
import com.codecool.hogwartshouses.exception.StudentNotFoundException;
import com.codecool.hogwartshouses.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void save(Student student) {
        studentDAO.save(student);
    }

    public void deleteById(Long id) {
        studentDAO.deleteById(id);

    }

    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    public Student getById(Long id) {
        Optional<Student> studentOptional = studentDAO.getById(id);
        if (studentOptional.isEmpty()) {
            throw new StudentNotFoundException("Student not found");
        }
        return studentOptional.get();
    }
}
