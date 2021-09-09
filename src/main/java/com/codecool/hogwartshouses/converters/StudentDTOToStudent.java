package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.StudentDTO;
import com.codecool.hogwartshouses.model.entity.Room;
import com.codecool.hogwartshouses.model.entity.Student;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOToStudent implements Converter<StudentDTO, Student> {
    @Override
    public Student convert(StudentDTO source) {
        if (source == null) {
            return null;
        }
        Student student = new Student();
        student.setId(source.getId());
        student.setName(source.getName());
        student.setHouseType(source.getHouseType());
        student.setPetType(source.getPetType());

        Room room = new Room();
        room.setId(source.getRoomId());
        student.setRoom(room);
        student.setPureBlood(source.isPureBlood());
        return student;
    }
}
