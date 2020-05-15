package com.mibe.tacocloud.data;

import com.mibe.tacocloud.model.Taco;

import java.sql.Types;
import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class TacoRepositoryJdbc implements TacoRepository {
    JdbcTemplate jdbc;

    @Autowired
    public TacoRepositoryJdbc(JdbcTemplate aJdbcTemp) {
        jdbc =aJdbcTemp;
    }
    
    @Override
    public Taco save(Taco aTaco) {
        long id=saveTacoInfo(aTaco);
        if(id==0) {
            System.err.println("Error inserting taco info!!!!");
            return null;
        }
        aTaco.setId(id);
        for(String ing:aTaco.getIngredients()) {
            saveIngredientOfTaco(ing, id);
        }
        return aTaco;
    }

    private long saveTacoInfo(Taco aTaco) {
        aTaco.setCreatedAtToNow();
        PreparedStatementCreatorFactory pscf=
            new PreparedStatementCreatorFactory("INSERT INTO Taco (name,createdAt) VALUES(?,?)",
                                                Types.VARCHAR,Types.TIMESTAMP);
        PreparedStatementCreator psc=
            pscf.newPreparedStatementCreator(Arrays.asList(aTaco.getName(),
                                                           Timestamp.valueOf(aTaco.getCreatedAt())));
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbc.update(psc,keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientOfTaco(String anIngredient,long aTacoId) {
        jdbc.update("INSERT INTO Taco_Ingredients (taco,ingredient) VALUES(?,?)",
                    aTacoId,anIngredient);
    }

}
