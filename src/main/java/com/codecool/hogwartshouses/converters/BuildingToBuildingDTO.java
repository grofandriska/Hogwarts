package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.BuildingDTO;
import com.codecool.hogwartshouses.model.entity.Building;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuildingToBuildingDTO implements Converter<Building, BuildingDTO> {
    @Override
    public BuildingDTO convert(Building building) {
        if (building == null) {
            return null;
        }
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(building.getId());
        buildingDTO.setName(building.getName());
        buildingDTO.setNumberOfRooms(building.getNumberOfRooms());
        return buildingDTO;
    }
}
