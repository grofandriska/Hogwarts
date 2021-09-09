package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.RecipeDTO;
import com.codecool.hogwartshouses.model.entity.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeDTO implements Converter<Recipe, RecipeDTO> {
    @Override
    public RecipeDTO convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setIng1(recipe.getIng1());
        recipeDTO.setIng2(recipe.getIng2());
        recipeDTO.setIng3(recipe.getIng3());
        recipeDTO.setIng4(recipe.getIng4());
        recipeDTO.setIng5(recipe.getIng5());
        return recipeDTO;
    }
}
