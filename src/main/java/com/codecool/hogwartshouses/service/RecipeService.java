package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.dao.RecipeDAO;
import com.codecool.hogwartshouses.dao.repository.RecipeDaoJdbcImpl;
import com.codecool.hogwartshouses.exception.RecipeNotFoundException;
import com.codecool.hogwartshouses.model.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeDAO recipeDAO;

    @Autowired
    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public void save(Recipe recipe) {
        recipeDAO.save(recipe);
    }

    public List<Recipe> getAll() {
        return recipeDAO.getAll();
    }

    public Recipe getById(Long id) {
        Optional<Recipe> recipeOptional = recipeDAO.getById(id);
        if (recipeOptional.isEmpty())
            throw new RecipeNotFoundException("Not found Recipe");
        return recipeOptional.get();
    }

    public void deleteById(Long id) {
        recipeDAO.deleteById(id);
    }

    public void saveByStudentId(Long id, Recipe recipe) {
        recipeDAO.saveByStudentId(id, recipe);
    }

    public void deleteByStudentAndRecipe(Long id, Long recipeId) {
        recipeDAO.deleteByStudentAndRecipe(id, recipeId);
    }

    public List<Recipe> getAllRecipeByStudentId(Long id) {
        return recipeDAO.getAllRecipeByStudentId(id);
    }

    public void updateRecipe(Long potionId, Long studentId, Long newPotionId) {
        recipeDAO.updateRecipe(potionId,studentId,newPotionId);
    }
}
