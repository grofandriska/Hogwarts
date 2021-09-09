package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.RoomDTO;
import com.codecool.hogwartshouses.model.entity.Room;
import com.codecool.hogwartshouses.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomDTOToRoom implements Converter<RoomDTO, Room> {
    private final BuildingService buildingService;

    @Autowired
    public RoomDTOToRoom(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @Override
    public Room convert(RoomDTO source) {
        if (source == null) {
            return null;
        }
        Room room = new Room();
        room.setId(source.getId());
        room.setRoomNumber(source.getRoomNumber());
        room.setCapacity(source.getCapacity());
        room.setNumberOfBeds(source.getNumberOfBeds());
        room.setBuilding(buildingService.getBuilding(source.getBuildingId()));
        return room;
    }
}
