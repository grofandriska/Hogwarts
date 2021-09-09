package com.codecool.hogwartshouses.model.DTO;

import com.codecool.hogwartshouses.model.entity.Room;
import com.codecool.hogwartshouses.model.entity.types.HouseType;
import com.codecool.hogwartshouses.model.entity.types.PetType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private HouseType houseType;
    private PetType petType;
    private Long roomId;
    private boolean pureBlood;
}
