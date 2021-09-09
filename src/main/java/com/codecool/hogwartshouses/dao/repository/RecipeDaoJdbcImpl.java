package com.codecool.hogwartshouses.dao.repository;

import com.codecool.hogwartshouses.dao.RecipeDAO;
import com.codecool.hogwartshouses.mapper.RecipeMapper;
import com.codecool.hogwartshouses.model.entity.Recipe;
import com.codecool.hogwartshouses.model.entity.types.IngredientType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
@Slf4j
public class RecipeDaoJdbcImpl implements RecipeDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RecipeMapper recipeMapper;

    @Autowired
    public RecipeDaoJdbcImpl(JdbcTemplate jdbcTemplate, RecipeMapper recipeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public void save(Recipe recipe) {
        long id = isRecipePresent(recipe);
        if (recipe.getId() == null) {
            if (id == -1) {
                if (recipe.getName().isBlank()) {
                    insertRecipeAndReturnId(recipe);
                } else {
                    insert(recipe);
                }
            }
        } else update(recipe);
    }

    private void update(Recipe recipe) {
        String sql = "UPDATE recipe SET name=?, ing1=?,ing2=?,ing3=?,ing4=?,ing5=? WHERE id=?;";
        int affectedRows = jdbcTemplate.update(sql, recipe.getName(), recipe.getIng1().name(), recipe.getIng2().name(), recipe.getIng3().name(), recipe.getIng4().name(), recipe.getIng5().name(), recipe.getId());
        log.info(affectedRows == 0 ? "Update error" : "Update success");
    }

    private void insert(Recipe recipe) {
        String sql = "INSERT INTO recipe (name,ing1,ing2,ing3,ing4,ing5) VALUES(?,?,?,?,?,?)";
        int affectedRows = jdbcTemplate.update(sql, recipe.getName(), recipe.getIng1().name(), recipe.getIng2().name(), recipe.getIng3().name(), recipe.getIng4().name(), recipe.getIng5().name());
        log.info(affectedRows == 0 ? "Insert error" : "Insert success");
    }


    @Override
    public List<Recipe> getAll() {
        String sql = "SELECT * FROM recipe;";
        List<Recipe> recipeList = jdbcTemplate.query(sql, recipeMapper);
        if (recipeList.size() == 0) log.info("No recipe");
        return recipeList;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        String sql = "SELECT * FROM recipe WHERE id=?;";
        Optional<Recipe> optionalRecipe = Optional.ofNullable(jdbcTemplate.queryForObject(sql, recipeMapper, id));
        log.info("Returned Recipe Optional");
        return optionalRecipe;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM recipe WHERE id=?;";
        int affectedRows = jdbcTemplate.update(sql, id);
        log.info(affectedRows == 0 ? "Delete error" : "Delete success");
    }

    @Override
    public void saveByStudentId(Long id, Recipe recipe) {
        long rId = isRecipePresent(recipe);
        if (rId > 0) {
            Integer number = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM student_potion WHERE potion_id=? AND student_id=?;", Integer.class, rId, id);
            if (number == 0)
                jdbcTemplate.update("INSERT INTO student_potion (student_id, potion_id) VALUES (?,?);", id, rId);
        } else {
            long recipeId = insertRecipeAndReturnId(recipe);

            if (recipeId > 0) {
                jdbcTemplate.update("INSERT INTO student_potion (student_id, potion_id) VALUES (?,?);", id, recipeId);
            }
        }

    }

    private long isRecipePresent(Recipe recipe) {
        Set<IngredientType> ingredientTypes = new HashSet<>(Arrays.asList(recipe.getIng1(), recipe.getIng2(), recipe.getIng3(), recipe.getIng4(), recipe.getIng5()));

        List<Recipe> recipeList = this.getAll();

        Optional<Recipe> optionalRecipe = recipeList.stream().filter(a ->
                ingredientTypes.equals(new HashSet<>(Arrays.asList(a.getIng1(), a.getIng2(), a.getIng3(), a.getIng4(), a.getIng5())))).findFirst();
        if (optionalRecipe.isPresent())
            return optionalRecipe.get().getId();
        else return -1;
    }

    @Override
    public void deleteByStudentAndRecipe(Long id, Long recipeId) {
        jdbcTemplate.update("DELETE FROM student_potion WHERE student_id=? and potion_id=?;", id, recipeId);
    }

    @Override
    public List<Recipe> getAllRecipeByStudentId(Long studentID) {
        String sql = "SELECT r.id, r.name, r.ing1, r.ing2, r.ing3, r.ing4, r.ing5 " +
                "FROM recipe r JOIN student_potion sp ON sp.potion_id=r.id " +
                "WHERE student_id=?;";
        List<Recipe> recipeList = jdbcTemplate.query(sql, recipeMapper, studentID);
        if (recipeList.size() == 0) {
            log.info("No recipe found");
        }
        return recipeList;

//        String sql = "SELECT * FROM recipe WHERE id IN (SELECT potion_id FROM student_potion WHERE student_id=?);";
//        List<Recipe> recipeList = jdbcTemplate.query(sql, recipeMapper, studentID);
//        return recipeList;
    }

    @Override
    public void updateRecipe(Long potionId, Long studentId, Long newPotionId) {
        String sql = "UPDATE student_potion SET potion_id=? WHERE student_id=? AND potion_id=?;";
        jdbcTemplate.update(sql, newPotionId, studentId, potionId);

    }

    private long insertRecipeAndReturnId(Recipe recipe) {
        String sql = "INSERT INTO recipe (name, ing1, ing2, ing3, ing4, ing5) VALUES (?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, RandomStringUtils.randomAlphabetic(10));
            ps.setString(2, recipe.getIng1().name());
            ps.setString(3, recipe.getIng2().name());
            ps.setString(4, recipe.getIng3().name());
            ps.setString(5, recipe.getIng4().name());
            ps.setString(6, recipe.getIng5().name());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
