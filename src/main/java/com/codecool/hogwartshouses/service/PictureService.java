package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.dao.PictureDAO;
import com.codecool.hogwartshouses.dao.RoomDAO;
import com.codecool.hogwartshouses.model.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {

    private final PictureDAO pictureDAO;
    private final RoomDAO roomDAO;

    @Autowired
    public PictureService(PictureDAO pictureDAO, RoomDAO roomDAO) {
        this.pictureDAO = pictureDAO;
        this.roomDAO = roomDAO;
    }

    public void addPicture(Picture picture) {
        pictureDAO.addPicture(picture);
    }

    public List<Picture> listPictures() {
        List<Picture> pictureList = pictureDAO.listPictures();
        pictureList.forEach(a -> {
            a.getBuilding().setRooms(roomDAO.getAllByBuildingId(a.getBuilding().getId()));
            a.getBuilding().setPictures(pictureDAO.getAllByBuildingId(a.getBuilding().getId()));
        });
        return pictureList;
    }

    public Picture getPicture(long id) {
        Picture picture = pictureDAO.getPicture(id);
        picture.getBuilding().setRooms(roomDAO.getAllByBuildingId(picture.getBuilding().getId()));
        picture.getBuilding().setPictures(pictureDAO.getAllByBuildingId(picture.getBuilding().getId()));
        return picture;
    }

    public void updatePicture(Picture picture, long id) {
        pictureDAO.updatePicture(picture, id);
    }

    public void deletePicture(long id) {
        pictureDAO.deletePicture(id);
    }
}
