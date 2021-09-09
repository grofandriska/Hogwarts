package com.codecool.hogwartshouses.model.DTO;

import com.codecool.hogwartshouses.model.entity.types.IngredientType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {
    private Long id;
    private String name;
    private IngredientType ing1;
    private IngredientType ing2;
    private IngredientType ing3;
    private IngredientType ing4;
    private IngredientType ing5;
}
