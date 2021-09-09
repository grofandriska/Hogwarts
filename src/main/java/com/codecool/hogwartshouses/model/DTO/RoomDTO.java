package com.codecool.hogwartshouses.model.DTO;

import com.codecool.hogwartshouses.model.entity.Building;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private Integer capacity;
    private Integer numberOfBeds;
    private Integer roomNumber;
    private Long buildingId;
}
