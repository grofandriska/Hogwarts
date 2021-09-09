package com.codecool.hogwartshouses.dao.repository;

import com.codecool.hogwartshouses.dao.PictureDAO;
import com.codecool.hogwartshouses.mapper.PictureMapper;
import com.codecool.hogwartshouses.model.entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class PictureDaoJdbcImpl implements PictureDAO {

    private PictureMapper pictureMapper;
    private JdbcTemplate jdbcTemplate;

    public PictureDaoJdbcImpl(PictureMapper pictureMapper, JdbcTemplate jdbcTemplate) {
        this.pictureMapper = pictureMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPicture(Picture picture) {
        if (picture.getId() == null)
            insert(picture);
        else update(picture);
    }

    private void insert(Picture picture) {
        String SQL = "INSERT INTO picture (name, building_id) VALUES (?, ?);";
        int update = jdbcTemplate.update(SQL, picture.getName(), picture.getBuilding().getId());
    }

    private void update(Picture picture) {
        String SQL = "UPDATE picture SET name=?, building_id=? WHERE id=?;";
        try {
            int update = jdbcTemplate.update(SQL, picture.getName(),picture.getBuilding().getId(), picture.getId());
            log.info(update == 1 ? "Picture updated" : "Update error" );
        } catch (DataAccessException ex) {
            log.info("Picture with requested id not found!");
        }
    }

    @Override
    public List<Picture> listPictures() {
        String SQL = "SELECT * FROM picture;";
        return jdbcTemplate.query(SQL, pictureMapper);
    }

    @Override
    public Picture getPicture(long id) {
        String SQL = "SELECT * FROM picture WHERE id=?;";
        Picture picture = null;
        try {
            picture = jdbcTemplate.queryForObject(SQL, pictureMapper, id);
        } catch (DataAccessException ex) {
            log.info("Picture with requested id not found!");
        }
        return picture;
    }

    @Override
    public void updatePicture(Picture picture, long id) {
        String SQL = "UPDATE picture SET name=?, building_id=? WHERE id=?;";
        try {
            int update = jdbcTemplate.update(SQL, picture.getName(),picture.getBuilding().getId(), id);
            log.info(update == 1 ? "Picture updated" : "Update error" );
        } catch (DataAccessException ex) {
            log.info("Picture with requested id not found!");
        }
    }

    @Override
    public void deletePicture(long id) {
        String SQL = "DELETE FROM picture WHERE id=?;";
        try {
            int update = jdbcTemplate.update(SQL, id);
            log.info(update == 1 ? "Picture deleted" : "Delete error");
        } catch (DataAccessException ex) {
            log.info("Picture with requested id not found!");
        }
    }

    @Override
    public List<Picture> getAllByBuildingId(Long id) {
        String sql="SELECT * FROM picture WHERE building_id=?;";
        List<Picture> pictureList= jdbcTemplate.query(sql,pictureMapper,id);
        return pictureList;
    }
}
