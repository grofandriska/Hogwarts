package com.codecool.hogwartshouses.dao;

import com.codecool.hogwartshouses.model.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeDAO {
    void save(Recipe recipe);

    List<Recipe> getAll();

    Optional<Recipe> getById(Long id);

    void deleteById(Long id);

    void saveByStudentId(Long id, Recipe recipe);

    void deleteByStudentAndRecipe(Long id, Long recipeId);

    List<Recipe> getAllRecipeByStudentId(Long studentID);

    void updateRecipe(Long potionId, Long studentId, Long newPotionId);
}
