package com.mibe.tacocloud.data;

import com.mibe.tacocloud.model.Ingredient;

import java.sql.SQLException;
import java.sql.ResultSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientRepositoryJdbc implements IngredientRepository {
    private final JdbcTemplate jdbc;
    
    public IngredientRepositoryJdbc(JdbcTemplate aJdbc) {
        jdbc =aJdbc;
    }

    private static Ingredient mapRowToIngredient(ResultSet aRs,int aRowCnt)
        throws SQLException {
        return new Ingredient(aRs.getString("ID"),
                              aRs.getString("NAME"),
                              Ingredient.Type.valueOf(aRs.getString("TYPE")));
    }
    
    @Override
    public Ingredient findOne(String aId) {
        return jdbc.queryForObject(
                "SELECT ID,NAME,TYPE FROM INGREDIENT where id=?",
                IngredientRepositoryJdbc::mapRowToIngredient,aId);
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("SELECT ID,NAME,TYPE FROM INGREDIENT",
                IngredientRepositoryJdbc::mapRowToIngredient);
    }

    @Override
    public Ingredient save(Ingredient aNewIngredient) {
        jdbc.update("INSERT INTO INGREDIENT (ID,NAME,TYPE) VALUES(?,?,?)",
                aNewIngredient.id(),
                aNewIngredient.name(),
                aNewIngredient.type().toString());
        return aNewIngredient;
    }

}
