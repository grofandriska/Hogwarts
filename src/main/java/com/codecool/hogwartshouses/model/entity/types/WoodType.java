package com.codecool.hogwartshouses.model.entity.types;

import com.codecool.hogwartshouses.converters.EnumToString;

public enum WoodType {

    YELLOW_BIRCH, BUTTERNUT, BLACK_CHERRY, PIN_CHERRY, AMERICAN_CHESTNUT;

    public String getName() {
        return EnumToString.convert(this);
    }

}
