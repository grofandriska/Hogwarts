package com.codecool.hogwartshouses.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BuildingDTO {
    private Long id;
    private String name;
    private Integer numberOfRooms;
}
