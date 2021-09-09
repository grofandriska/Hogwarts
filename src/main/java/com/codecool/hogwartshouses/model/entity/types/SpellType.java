package com.codecool.hogwartshouses.model.entity.types;

import com.codecool.hogwartshouses.converters.EnumToString;

public enum SpellType {
    FLY,
    LEVITATE,
    ABRAKADABRA,
    HOCUS_POK,
    LUMOS;

    public String getName() {
        return EnumToString.convert(this);
    }

}
