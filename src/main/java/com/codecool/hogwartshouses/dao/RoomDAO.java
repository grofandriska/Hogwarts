package com.codecool.hogwartshouses.dao;

import com.codecool.hogwartshouses.model.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomDAO {
    void save(Room room);

    void deleteById(Long id);

    List<Room> getAll();

    Optional<Room> findById(Long id);

    List<Room> getAvailableRooms();

    List<Room> getNoCatOrOwlRooms();

    List<Room> getAllByBuildingId(Long id);
}
