package com.codecool.hogwartshouses.model.entity.types;

import com.codecool.hogwartshouses.converters.EnumToString;

public enum IngredientType {
    DOG_EAR,
    FROG_SALIVA,
    GOAT_EXCREMENT,
    BIRD_HAIR,
    CAT_TAIL,
    ELEPHANT_TUSK,
    BIRD_FEATHERS,
    BAT_BLOOD,
    MADONNA_ARMPIT_HAIR,
    DONKEY_HOOF;

    public String getName() {
        return EnumToString.convert(this);
    }
}
