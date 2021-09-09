package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.dao.RoomDAO;
import com.codecool.hogwartshouses.model.entity.Student;
import com.codecool.hogwartshouses.model.entity.types.HouseType;
import com.codecool.hogwartshouses.model.entity.types.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper<Student> {
    private RoomDAO roomDAO;

    @Autowired
    public StudentMapper(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setHouseType(HouseType.valueOf(resultSet.getString("house_type")));
        student.setPetType(PetType.valueOf(resultSet.getString("pet_type")));
        if (resultSet.getLong("room_id") != 0) {
            student.setRoom(roomDAO.findById(resultSet.getLong("room_id")).get());
        }
        student.setPureBlood(resultSet.getBoolean("pure_blood"));

        return student;
    }
}
