package com.codecool.hogwartshouses.dao;

import com.codecool.hogwartshouses.model.entity.Picture;

import java.util.List;

public interface PictureDAO {
    void addPicture(Picture picture);

    List<Picture> listPictures();

    Picture getPicture(long id);

    void updatePicture(Picture picture, long id);

    void deletePicture(long id);

    List<Picture> getAllByBuildingId(Long id);
}
