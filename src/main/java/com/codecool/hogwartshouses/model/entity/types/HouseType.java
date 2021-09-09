package com.codecool.hogwartshouses.model.entity.types;

import com.codecool.hogwartshouses.converters.EnumToString;

public enum HouseType {
    GRYFFINDOR,
    HUFFLEPUFF,
    RAVENCLAW,
    SLYTHERIN;

    public String getName() {
        return EnumToString.convert(this);
    }
}
