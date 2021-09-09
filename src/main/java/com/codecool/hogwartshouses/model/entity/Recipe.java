package com.codecool.hogwartshouses.model.entity;

import com.codecool.hogwartshouses.model.entity.types.IngredientType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    private Long id;
    private String name;
    private IngredientType ing1;
    private IngredientType ing2;
    private IngredientType ing3;
    private IngredientType ing4;
    private IngredientType ing5;
}
