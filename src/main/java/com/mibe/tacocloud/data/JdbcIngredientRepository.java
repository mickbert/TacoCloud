package com.mibe.tacocloud.data;

import com.mibe.tacocloud.Ingredient;

import java.sql.SQLException;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate m_jdbc;
    
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate aJdbc) {
        m_jdbc=aJdbc;
    }

    private static Ingredient mapRowToIngredient(ResultSet aRs,int aRowCnt)
        throws SQLException {
        return new Ingredient(aRs.getString("ID"),
                              aRs.getString("NAME"),
                              Ingredient.Type.valueOf(aRs.getString("TYPE")));
    }
    
    @Override
    public Ingredient findOne(String aId) {
        return m_jdbc.queryForObject(
                "SELECT ID,NAME,TYPE FROM INGREDIENT where id=?",
                JdbcIngredientRepository::mapRowToIngredient,aId);
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return m_jdbc.query("SELECT ID,NAME,TYPE FROM INGREDIENT",
                JdbcIngredientRepository::mapRowToIngredient);
    }

    @Override
    public Ingredient save(Ingredient aNewIngredient) {
        m_jdbc.update("INSERT INTO INGREDIENT (ID,NAME,TYPE) VALUES(?,?,?)",
                aNewIngredient.id(),
                aNewIngredient.name(),
                aNewIngredient.type().toString());
        return aNewIngredient;
    }

}
