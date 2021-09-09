package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.dao.BuildingDAO;
import com.codecool.hogwartshouses.model.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoomMapper implements RowMapper<Room> {
    private BuildingDAO buildingDAO;
    @Autowired
    public RoomMapper(BuildingDAO buildingDAO) {
        this.buildingDAO = buildingDAO;
    }

    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setCapacity(resultSet.getInt("capacity"));
        room.setRoomNumber(resultSet.getInt("room_number"));
        room.setNumberOfBeds(resultSet.getInt("number_of_beds"));
        room.setBuilding(buildingDAO.getBuilding(resultSet.getLong("building_id")));
        return room;
    }
}
