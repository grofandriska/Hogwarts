package com.codecool.hogwartshouses.model.DTO;

import com.codecool.hogwartshouses.model.entity.Building;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PictureDTO {
        private Long id;
        private String name;
        private long buildingId;
}

