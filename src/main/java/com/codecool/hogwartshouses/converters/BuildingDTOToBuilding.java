package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.BuildingDTO;
import com.codecool.hogwartshouses.model.entity.Building;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuildingDTOToBuilding implements Converter<BuildingDTO, Building> {
    @Override
    public Building convert(BuildingDTO source) {
        if (source == null) {
            return null;
        }
        Building building = new Building();
        building.setId(source.getId());
        building.setName(source.getName());
        building.setNumberOfRooms(source.getNumberOfRooms());
        return building;
    }
}
