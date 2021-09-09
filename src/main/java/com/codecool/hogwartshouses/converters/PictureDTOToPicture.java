package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.PictureDTO;
import com.codecool.hogwartshouses.model.entity.Building;
import com.codecool.hogwartshouses.model.entity.Picture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PictureDTOToPicture implements Converter<PictureDTO, Picture> {
    @Override
    public Picture convert(PictureDTO source) {
        if (source == null) {
            return null;
        }
        Picture picture = new Picture();
        picture.setId(source.getId());
        picture.setName(source.getName());

        Building building = new Building();
        building.setId(source.getBuildingId());
        picture.setBuilding(building);
        return picture;
    }
}
