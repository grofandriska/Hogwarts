package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.RoomDTO;
import com.codecool.hogwartshouses.model.entity.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomToRoomDTO implements Converter<Room, RoomDTO> {

    @Override
    public RoomDTO convert(Room room) {
        if (room == null) {
            return null;
        }
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        roomDTO.setBuildingId(room.getBuilding().getId());
        return roomDTO;
    }
}
