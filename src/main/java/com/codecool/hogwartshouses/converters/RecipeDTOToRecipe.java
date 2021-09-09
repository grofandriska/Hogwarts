package com.codecool.hogwartshouses.converters;

import com.codecool.hogwartshouses.model.DTO.RecipeDTO;
import com.codecool.hogwartshouses.model.entity.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeDTOToRecipe implements Converter<RecipeDTO, Recipe> {
    @Override
    public Recipe convert(RecipeDTO source) {
        if (source == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setName(source.getName());
        recipe.setIng1(source.getIng1());
        recipe.setIng2(source.getIng2());
        recipe.setIng3(source.getIng3());
        recipe.setIng4(source.getIng4());
        recipe.setIng5(source.getIng5());
        return recipe;
    }
}
