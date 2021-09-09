package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.dao.BuildingDAO;
import com.codecool.hogwartshouses.model.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PictureMapper implements RowMapper<Picture> {
    private BuildingDAO buildingDAO;

    @Autowired
    public PictureMapper(BuildingDAO buildingDAO) {
        this.buildingDAO = buildingDAO;
    }

    @Override
    public Picture mapRow(ResultSet resultSet, int i) throws SQLException {
        Picture picture = new Picture();
        picture.setId(resultSet.getLong("id"));
        picture.setName(resultSet.getString("name"));
        picture.setBuilding(buildingDAO.getBuilding(resultSet.getLong("building_id")));
        return picture;
    }
}
