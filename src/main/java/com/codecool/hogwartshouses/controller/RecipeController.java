package com.codecool.hogwartshouses.controller;

import com.codecool.hogwartshouses.converters.RecipeDTOToRecipe;
import com.codecool.hogwartshouses.converters.RecipeToRecipeDTO;
import com.codecool.hogwartshouses.model.DTO.RecipeDTO;
import com.codecool.hogwartshouses.model.DTO.StudentDTO;
import com.codecool.hogwartshouses.model.entity.Recipe;
import com.codecool.hogwartshouses.model.entity.Student;
import com.codecool.hogwartshouses.model.entity.types.IngredientType;
import com.codecool.hogwartshouses.service.RecipeService;
import com.codecool.hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeToRecipeDTO recipeToRecipeDTO;
    private final RecipeDTOToRecipe recipeDTOToRecipe;

    @Autowired
    public RecipeController(RecipeService recipeService,
                            RecipeToRecipeDTO recipeToRecipeDTO,
                            RecipeDTOToRecipe recipeDTOToRecipe) {
        this.recipeService = recipeService;
        this.recipeToRecipeDTO = recipeToRecipeDTO;
        this.recipeDTOToRecipe = recipeDTOToRecipe;
    }

    @GetMapping
    public String getRecipePage(Model model){
        model.addAttribute("ingredients", IngredientType.values());
        model.addAttribute("recipe", new RecipeDTO());
        model.addAttribute("recipes", recipeService.getAll());
        return "recipes";
    }

    @PostMapping
    public String saveOrUpdateRecipe(@ModelAttribute RecipeDTO recipeDTO) {
        Recipe recipe = recipeDTOToRecipe.convert(recipeDTO);

        recipeService.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/{id}/update")
    public String updateStudent(@PathVariable Long id, Model model) {
        RecipeDTO recipeDTO = recipeToRecipeDTO.convert(recipeService.getById(id));

        model.addAttribute("recipe", recipeDTO);
        model.addAttribute("ingredients", IngredientType.values());
        model.addAttribute("recipes", recipeService.getAll());
        return "recipes";
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipeById(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/recipes";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Recipe> getAll() {
        return recipeService.getAll();
    }

    @GetMapping("/{id}/get")
    @ResponseBody
    public Recipe getById(@PathVariable("id") Long id) {
        return recipeService.getById(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public void save(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
    }

    @PostMapping("/student/{id}/add")
    @ResponseBody
    public void saveByStudentId(@PathVariable("id") Long id, @RequestBody Recipe recipe) {
         recipeService.saveByStudentId(id,recipe);
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    public void update(@PathVariable("id") Long id,
                       @RequestBody Recipe recipe) {
        recipe.setId(id);
        recipeService.save(recipe);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseBody
    public void deleteById(@PathVariable("id") Long id) {
        recipeService.deleteById(id);
    }

    @DeleteMapping("/student/{id}/delete")
    @ResponseBody
    public void deleteByStudentAndRecipe(@PathVariable("id") Long id,@RequestParam Long recipeId) {
        recipeService.deleteByStudentAndRecipe(id,recipeId);
    }

    @GetMapping("/student/{id}/all")
    @ResponseBody
    public List<Recipe> getAllRecipeByStudentId(@PathVariable("id") Long id){
        return recipeService.getAllRecipeByStudentId(id);
    }

    @PutMapping("/{potionId}/student/{studentId}/update")
    @ResponseBody
    public void updateRecipe(@PathVariable("potionId") Long potionId,
                             @PathVariable("studentId") Long studentId,
                             @RequestParam(name = "id") Long newPotionId ){
        recipeService.updateRecipe(potionId,studentId,newPotionId);
    }


}
