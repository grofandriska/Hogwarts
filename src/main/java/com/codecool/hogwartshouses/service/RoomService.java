package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.dao.RoomDAO;
import com.codecool.hogwartshouses.dao.StudentDAO;
import com.codecool.hogwartshouses.exception.RoomNotFoundException;
import com.codecool.hogwartshouses.model.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomDAO roomDAO;
    private final StudentDAO studentDAO;

    @Autowired
    public RoomService(RoomDAO roomDAO, StudentDAO studentDAO) {
        this.roomDAO = roomDAO;
        this.studentDAO = studentDAO;
    }

    public void save(Room room) {
        roomDAO.save(room);
    }

    public void deleteById(Long id) {
        roomDAO.deleteById(id);

    }

    public List<Room> getAll() {
        List<Room> rooms = roomDAO.getAll();
        rooms.forEach(a -> a.getResidents().addAll(studentDAO.getAllByRoomId(a.getId())));
        return rooms;
    }

    public Room getById(Long id) {
        Optional<Room> roomOptional = roomDAO.findById(id);
        if (roomOptional.isEmpty()) {
            throw new RoomNotFoundException("Room not found");
        }
        return roomOptional.get();
    }

    public List<Room> getAvailableRooms(){
        return roomDAO.getAvailableRooms();
    }

    public List<Room> getNoCatOrOwlRooms() {
        return roomDAO.getNoCatOrOwlRooms();
    }
}
