package com.codecool.hogwartshouses.model.DTO;

import com.codecool.hogwartshouses.model.entity.types.SpellType;
import lombok.*;


@Getter
@Setter
public class SpellDTO {
    private Long id;
    private SpellType name;
    private int power;
    private boolean banned;
}
