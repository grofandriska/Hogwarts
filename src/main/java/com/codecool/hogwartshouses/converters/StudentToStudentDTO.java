package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.StudentDTO;
import com.codecool.hogwartshouses.model.entity.Student;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentToStudentDTO implements Converter<Student, StudentDTO> {
    @Override
    public StudentDTO convert(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setHouseType(student.getHouseType());
        studentDTO.setPetType(student.getPetType());
        studentDTO.setRoomId(student.getRoom().getId());
        studentDTO.setPureBlood(student.isPureBlood());
        return studentDTO;
    }
}
