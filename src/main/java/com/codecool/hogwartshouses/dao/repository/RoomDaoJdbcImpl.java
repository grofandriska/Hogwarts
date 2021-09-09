package com.codecool.hogwartshouses.dao.repository;

import com.codecool.hogwartshouses.dao.RoomDAO;
import com.codecool.hogwartshouses.mapper.PetTypeMapper;
import com.codecool.hogwartshouses.mapper.RoomMapper;
import com.codecool.hogwartshouses.model.entity.Room;
import com.codecool.hogwartshouses.model.entity.types.PetType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class RoomDaoJdbcImpl implements RoomDAO {
    private JdbcTemplate jdbcTemplate;
    private RoomMapper roomMapper;
    private PetTypeMapper petTypeMapper;

    @Autowired
    public RoomDaoJdbcImpl(JdbcTemplate jdbcTemplate, RoomMapper roomMapper, PetTypeMapper petTypeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.roomMapper = roomMapper;
        this.petTypeMapper = petTypeMapper;
    }

    @Override
    public void save(Room room) {
        if (room.getId() == null)
            insert(room);
        else update(room);
    }


    private void insert(Room room) {
        String sql = "INSERT INTO room (capacity,number_of_beds,room_number,building_id) VALUES (?,?,?,?);";
        int affectedRows = jdbcTemplate.update(sql, room.getCapacity(), room.getNumberOfBeds(), room.getRoomNumber(),
                room.getBuilding().getId());
        log.info(affectedRows == 0 ? "Insert error" : "Insert success");
    }

    private void update(Room room) {
        String sql = "UPDATE room SET capacity=?,number_of_beds=?,room_number=?, building_id=? WHERE id=?;";
        int affectedRows = jdbcTemplate.update(sql, room.getCapacity(), room.getNumberOfBeds(), room.getRoomNumber(),
                room.getBuilding().getId(), room.getId());
        log.info(affectedRows == 0 ? "Update error" : "Update success");
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM room WHERE id=?;";
        int affectedRows = jdbcTemplate.update(sql, id);
        log.info(affectedRows == 0 ? "Delete error" : "Delete success");
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms;
        String sql = "SELECT * FROM room;";
        rooms = jdbcTemplate.query(sql, roomMapper);
        if (rooms.size() == 0) log.info("No rooms");
        return rooms;
    }

    @Override
    public Optional<Room> findById(Long id) {
        String sql = "SELECT * FROM room WHERE id=?;";
        Room room = jdbcTemplate.queryForObject(sql, roomMapper, id);
        log.info("Returned Room Optional");
        return Optional.of(room);
    }

    @Override
    public List<Room> getAvailableRooms() {
        String sql = "SELECT * FROM room WHERE capacity > (SELECT COUNT(id) FROM student where room_id = id)";
        return jdbcTemplate.query(sql, roomMapper);
    }

    @Override
    public List<Room> getNoCatOrOwlRooms() {
        List<Room> validRooms = new ArrayList<>();
        for (Room room : getAll()) {
            List<PetType> allPets = jdbcTemplate.query("SELECT pet_type FROM student WHERE room_id=?;", petTypeMapper, room.getId());
            if (!(allPets.contains(PetType.OWL) || allPets.contains(PetType.CAT))) {
                validRooms.add(room);
            }
        }
        return validRooms;
    }

    @Override
    public List<Room> getAllByBuildingId(Long id) {
        String sql = "SELECT * FROM room WHERE building_id=?;";
        List<Room> roomList = jdbcTemplate.query(sql, roomMapper, id);
        return roomList;
    }
}
