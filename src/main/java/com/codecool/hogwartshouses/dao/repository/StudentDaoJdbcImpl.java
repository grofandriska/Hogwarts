package com.codecool.hogwartshouses.dao.repository;

import com.codecool.hogwartshouses.dao.StudentDAO;
import com.codecool.hogwartshouses.mapper.PetTypeMapper;
import com.codecool.hogwartshouses.mapper.StudentMapper;
import com.codecool.hogwartshouses.model.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class StudentDaoJdbcImpl implements StudentDAO {
    private final JdbcTemplate jdbcTemplate;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentDaoJdbcImpl(JdbcTemplate jdbcTemplate, StudentMapper studentMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentMapper = studentMapper;
    }

    @Override
    public void save(Student student) {
        if (student.getId() == null)
            insert(student);
        else update(student);
    }

    private void insert(Student student) {
        String sql = "INSERT INTO student (name,house_type,pet_type,room_id,pure_blood) VALUES (?,?,?,?,?);";
        int affectedRows = jdbcTemplate.update(sql, student.getName(), student.getHouseType().name(), student.getPetType().name(),
                student.getRoom() == null ? null : student.getRoom().getId(), student.isPureBlood());
        log.info(affectedRows == 0 ? "Insert error" : "Insert success");
    }

    private void update(Student student) {
        String sql = "UPDATE student SET name=?,house_type=?,pet_type=?,room_id=?,pure_blood=? WHERE id=?;";
        int affectedRows = jdbcTemplate.update(sql, student.getName(), student.getHouseType().name(), student.getPetType().name(),
                student.getRoom() == null ? null : student.getRoom().getId(), student.isPureBlood(), student.getId());
        log.info(affectedRows == 0 ? "Update error" : "Update success");
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM student WHERE id=?;";
        int affectedRows = jdbcTemplate.update(sql, id);
        log.info(affectedRows == 0 ? "Delete error" : "Delete success");
    }

    @Override
    public List<Student> getAll() {
        List<Student> students;
        String sql = "SELECT * FROM student;";
        students = jdbcTemplate.query(sql, studentMapper);
        if (students.size() == 0) log.info("No students");
        return students;
    }

    @Override
    public Optional<Student> getById(Long id) {
        String sql = "SELECT * FROM student WHERE id=?;";
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject(sql, studentMapper, id);
        } catch (Exception e) {
            return Optional.empty();
        }
        log.info("Returned Student Optional");
        return Optional.of(student);
    }

    @Override
    public List<Student> getAllByRoomId(Long id) {
        String sql = "SELECT * FROM student where room_id = ?";
        return jdbcTemplate.query(sql, studentMapper, id);
    }
}

