package com.codecool.hogwartshouses.mapper;

import com.codecool.hogwartshouses.model.entity.Recipe;
import com.codecool.hogwartshouses.model.entity.types.IngredientType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecipeMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
        return Recipe.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .ing1(IngredientType.valueOf(resultSet.getString("ing1")))
                .ing2(IngredientType.valueOf(resultSet.getString("ing2")))
                .ing3(IngredientType.valueOf(resultSet.getString("ing3")))
                .ing4(IngredientType.valueOf(resultSet.getString("ing4")))
                .ing5(IngredientType.valueOf(resultSet.getString("ing5")))
                .build();
    }
}
