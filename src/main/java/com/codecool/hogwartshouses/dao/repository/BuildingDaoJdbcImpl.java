package com.codecool.hogwartshouses.dao.repository;

import com.codecool.hogwartshouses.dao.BuildingDAO;
import com.codecool.hogwartshouses.mapper.BuildingMapper;
import com.codecool.hogwartshouses.model.entity.Building;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class BuildingDaoJdbcImpl implements BuildingDAO {


    private BuildingMapper buildingMapper;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BuildingDaoJdbcImpl(BuildingMapper buildingMapper, JdbcTemplate jdbcTemplate) {
        this.buildingMapper = buildingMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBuilding(Building building) {
        if (building.getId() == null)
            insert(building);
        else update(building);
    }

    private void insert(Building building) {
        String sql = "INSERT INTO building(name, id,number_of_rooms) VALUES(?, ?,?)";
        jdbcTemplate.update(sql, building.getName(), building.getId(), building.getNumberOfRooms());
    }

    private void update(Building building) {
        String sql = "UPDATE building SET name=?, number_of_rooms=? WHERE id=?;";
        jdbcTemplate.update(sql, building.getName(), building.getNumberOfRooms(), building.getId());
    }


    @Override
    public List<Building> listBuildings() {
        String sql = "SELECT * FROM building;";
        return jdbcTemplate.query(sql, buildingMapper);

    }

    @Override
    public Building getBuilding(long id) {
        Building response = null;
        String sql = "SELECT * FROM building WHERE id= ?";
        try {
            //response = jdbcTemplate.queryForObject(sql, new Object[]{id}, buildingMapper);
            response = jdbcTemplate.queryForObject(sql, buildingMapper, id);
        } catch (DataAccessException e) {

            e.printStackTrace();
        }
        return response;
        //        String sql = "SELECT * FROM building where id = ? ";
        //        Building response = jdbcTemplate.queryForObject(sql, buildingMapper, id);
        //        return response;

    }

    @Override
    public void updateBuilding(Building building, long id) {
        String sql = "UPDATE building SET name=?, number_of_rooms=? WHERE id=?;";
        int index = jdbcTemplate.update(sql,building.getName(),building.getNumberOfRooms(), id);
        if (index == 1) {
            System.out.println("Record has been updated by id:" + id);
        }

    }

    @Override
    public void deleteBuilding(long id) {
        jdbcTemplate.update("DELETE FROM building WHERE id = ?;", id);

    }
}