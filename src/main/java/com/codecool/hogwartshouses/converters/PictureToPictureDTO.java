package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.PictureDTO;
import com.codecool.hogwartshouses.model.entity.Picture;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PictureToPictureDTO implements Converter<Picture, PictureDTO> {
    @Override
    public PictureDTO convert(Picture picture) {
        if (picture == null) {
            return null;
        }
        PictureDTO pictureDTO = new PictureDTO();
        pictureDTO.setId(picture.getId());
        pictureDTO.setName(picture.getName());
        pictureDTO.setBuildingId(picture.getBuilding().getId());
        return pictureDTO;
    }
}
