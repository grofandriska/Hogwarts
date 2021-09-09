package com.codecool.hogwartshouses.model.DTO;

import com.codecool.hogwartshouses.model.entity.Spell;
import com.codecool.hogwartshouses.model.entity.Teacher;
import com.codecool.hogwartshouses.model.entity.types.ColorType;
import com.codecool.hogwartshouses.model.entity.types.WoodType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class WandDTO {
    private Long id;
    private WoodType woodType;
    private ColorType colorType;
    private Set<SpellDTO> spells;
}
