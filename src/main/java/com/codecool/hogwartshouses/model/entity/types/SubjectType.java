package com.codecool.hogwartshouses.model.entity.types;

import com.codecool.hogwartshouses.converters.EnumToString;

public enum SubjectType {
    MATH,
    HISTORY,
    SPELLS,
    DARK_SPELLS;

    public String getName() {
        return EnumToString.convert(this);
    }
}
