package com.codecool.hogwartshouses.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@JsonIgnoreProperties(value = {"residents"})
@Getter
@Setter
@NoArgsConstructor
public class Room {
    private Long id;
    private Integer capacity;
    private Integer numberOfBeds;
    private Integer roomNumber;

    private Building building;

    private Set<Student> residents = new HashSet<>();

    public void addResident(Student student) {
        if (student.getRoom() == null)
            student.setRoom(this);
        residents.add(student);
    }

    public boolean hasEmptyBed() {
        return capacity > residents.size();
    }

}
