package com.codecool.hogwartshouses.model.entity;

import com.codecool.hogwartshouses.model.entity.types.HouseType;
import com.codecool.hogwartshouses.model.entity.types.PetType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(value = {"room"})
@Getter
@Setter
@NoArgsConstructor
public class Student {
    private Long id;
    private String name;
    private HouseType houseType;
    private PetType petType;
    private Room room;
    private boolean pureBlood;
}
