package com.codecool.hogwartshouses.dao;

import com.codecool.hogwartshouses.model.entity.Building;

import java.util.List;

public interface BuildingDAO {

    void addBuilding(Building building);

    List<Building> listBuildings();

    Building getBuilding(long id);

    void updateBuilding(Building building, long id);

    void deleteBuilding(long id);

}