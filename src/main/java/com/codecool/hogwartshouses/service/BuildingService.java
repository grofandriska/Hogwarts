package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.dao.BuildingDAO;
import com.codecool.hogwartshouses.dao.PictureDAO;
import com.codecool.hogwartshouses.dao.RoomDAO;
import com.codecool.hogwartshouses.model.entity.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingDAO buildingDAO;
    private final RoomDAO roomDAO;
    private final PictureDAO pictureDAO;

    @Autowired
    public BuildingService(BuildingDAO buildingDAO, RoomDAO roomDAO, PictureDAO pictureDAO) {
        this.buildingDAO = buildingDAO;
        this.roomDAO = roomDAO;
        this.pictureDAO = pictureDAO;
    }

    public void addBuilding(Building building) {
        buildingDAO.addBuilding(building);
    }

    public List<Building> listBuildings() {
        List<Building> buildingList = buildingDAO.listBuildings();
        buildingList.forEach(a -> {
            a.setPictures(pictureDAO.getAllByBuildingId(a.getId()));
            a.setRooms(roomDAO.getAllByBuildingId(a.getId()));
        });
        return buildingList;
    }

    public Building getBuilding(long id) {
        Building building = buildingDAO.getBuilding(id);
        building.setRooms(roomDAO.getAllByBuildingId(building.getId()));
        building.setPictures(pictureDAO.getAllByBuildingId(building.getId()));
        return building;
    }

    public void updateBuilding(Building building, long id) {
        buildingDAO.updateBuilding(building, id);
    }

    public void deleteBuilding(long id) {
        buildingDAO.deleteBuilding(id);
    }

}
