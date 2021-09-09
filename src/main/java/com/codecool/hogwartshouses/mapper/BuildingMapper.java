package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.model.entity.Building;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BuildingMapper implements RowMapper<Building> {

    @Override
    public Building mapRow(ResultSet resultSet, int i) throws SQLException {
        Building building = new Building();
        building.setId(resultSet.getLong("id"));
        building.setName(resultSet.getString("name"));
        building.setNumberOfRooms(resultSet.getInt("number_of_rooms"));
        return building;
    }
}