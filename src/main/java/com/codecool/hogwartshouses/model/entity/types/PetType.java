package com.codecool.hogwartshouses.model.entity.types;

import com.codecool.hogwartshouses.converters.EnumToString;

public enum PetType {
    CAT,
    RAT,
    OWL,
    NONE;

    public String getName() {
        return EnumToString.convert(this);
    }
}
