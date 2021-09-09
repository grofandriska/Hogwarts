package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.model.entity.types.PetType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PetTypeMapper implements RowMapper<PetType> {
    @Override
    public PetType mapRow(ResultSet resultSet, int i) throws SQLException {
        return PetType.valueOf(resultSet.getString("pet_type"));
    }
}
