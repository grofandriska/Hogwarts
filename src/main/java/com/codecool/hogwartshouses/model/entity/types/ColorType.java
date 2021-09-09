package com.codecool.hogwartshouses.model.entity.types;

import com.codecool.hogwartshouses.converters.EnumToString;

public enum ColorType {

    RED, BLUE, BROWN, BLACK, RAINBOW, YELLOW, WHITE, GREY;

    public String getName() {
        return EnumToString.convert(this);
    }

}
