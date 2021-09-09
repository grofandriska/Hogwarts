package com.codecool.hogwartshouses.service;

import com.codecool.hogwartshouses.dao.RecipeDAO;
import com.codecool.hogwartshouses.exception.RecipeNotFoundException;
import com.codecool.hogwartshouses.model.entity.Recipe;
import com.codecool.hogwartshouses.model.entity.types.IngredientType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    @Mock
    RecipeDAO recipeDAO;

    @InjectMocks
    RecipeService recipeService;

    @Test
    void getAll() {
        when(recipeDAO.getAll()).thenReturn(Arrays.asList(new Recipe(1L, "test1",
                        IngredientType.BAT_BLOOD,
                        IngredientType.BIRD_FEATHERS,
                        IngredientType.DOG_EAR,
                        IngredientType.FROG_SALIVA,
                        IngredientType.GOAT_EXCREMENT),
                new Recipe(2L, "test2",
                        IngredientType.ELEPHANT_TUSK,
                        IngredientType.DOG_EAR,
                        IngredientType.GOAT_EXCREMENT,
                        IngredientType.BAT_BLOOD,
                        IngredientType.MADONNA_ARMPIT_HAIR)));
        List<Recipe> responseRecipe = recipeService.getAll();
        assertEquals(2, responseRecipe.size());
        verify(recipeDAO).getAll();
        String name = responseRecipe.get(1).getName();
        assertEquals("test2", name);
    }

    @Test
    void getById() {
        Recipe recipe = new Recipe(1L, "test3",
                IngredientType.ELEPHANT_TUSK,
                IngredientType.DOG_EAR,
                IngredientType.GOAT_EXCREMENT,
                IngredientType.BAT_BLOOD,
                IngredientType.MADONNA_ARMPIT_HAIR);

        when(recipeDAO.getById(1L)).thenReturn(Optional.of(recipe));
        Recipe responseRecipe = recipeService.getById(1L);
        assertEquals(IngredientType.DOG_EAR, responseRecipe.getIng2());
        verify(recipeDAO).getById(1L);
    }

    @Test
    void getById_throwRecipeNotFoundException() {
        when(recipeDAO.getById(1L)).thenThrow(RecipeNotFoundException.class);
        assertThrows(RecipeNotFoundException.class, () -> recipeService.getById(1L));
        verify(recipeDAO).getById(1L);
    }

    @Test
    void getAllRecipeByStudentId() {
        Recipe recipe = new Recipe(1L, "test4",
                IngredientType.ELEPHANT_TUSK,
                IngredientType.DOG_EAR,
                IngredientType.GOAT_EXCREMENT,
                IngredientType.BAT_BLOOD,
                IngredientType.MADONNA_ARMPIT_HAIR);

        when(recipeDAO.getAllRecipeByStudentId(1L)).thenReturn(Arrays.asList(recipe));
        assertEquals("test4", recipeService.getAllRecipeByStudentId(1L).get(0).getName());
        verify(recipeDAO).getAllRecipeByStudentId(1L);
        assertEquals(1, recipeService.getAllRecipeByStudentId(1L).get(0).getId());

    }

    @Test
    public void deleteById_ArgumentTransfer() {
        ArgumentCaptor<Long> argumentId = ArgumentCaptor.forClass(Long.class);
        Mockito.doNothing().when(recipeDAO).deleteById(argumentId.capture());
        recipeService.deleteById(1L);
        verify(recipeDAO).deleteById(argumentId.capture());
        Long responseId = argumentId.getValue().longValue();
        assertEquals(1, responseId);
    }

    @Test
    public void saveByStudentId_ArgumentTransfer() {
        ArgumentCaptor<Long> argumentCaptorId= ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Recipe> argumentCaptorRecipe= ArgumentCaptor.forClass(Recipe.class);
        Recipe recipe = new Recipe(1L, "test",
                IngredientType.ELEPHANT_TUSK,
                IngredientType.DOG_EAR,
                IngredientType.GOAT_EXCREMENT,
                IngredientType.BAT_BLOOD,
                IngredientType.MADONNA_ARMPIT_HAIR);
        Mockito.doNothing().when(recipeDAO).saveByStudentId(argumentCaptorId.capture(),argumentCaptorRecipe.capture());
        recipeService.saveByStudentId(1L,recipe);
        verify(recipeDAO).saveByStudentId(argumentCaptorId.capture(),argumentCaptorRecipe.capture());
        String name=argumentCaptorRecipe.getValue().getName();
        assertEquals("test",name);


    }
}