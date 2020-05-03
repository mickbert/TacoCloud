package com.mibe.tacocloud.data;

import com.mibe.tacocloud.Taco;
import com.mibe.tacocloud.Ingredient;

import java.sql.Types;
import java.sql.Timestamp;
import java.util.Arrays;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class JdbcTacoRepository implements TacoRepository {
    JdbcTemplate m_jdbc;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate aJdbcTemp) {
        m_jdbc=aJdbcTemp;
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
        aTaco.setCreatedAt();
        PreparedStatementCreatorFactory pscf=
            new PreparedStatementCreatorFactory("INSERT INTO Taco (name,createdAt) VALUES(?,?)",
                                                Types.VARCHAR,Types.TIMESTAMP);
        PreparedStatementCreator psc=
            pscf.newPreparedStatementCreator(Arrays.asList(aTaco.getName(),
                                                           Timestamp.valueOf(aTaco.getCreatedAt())));
        KeyHolder keyHolder=new GeneratedKeyHolder();
        m_jdbc.update(psc,keyHolder);
        if(keyHolder.getKey()==null) {
            System.err.println("Error obtaining taco id!!!! Workaround: SELECT MAX(ID)");
            return m_jdbc.queryForObject("SELECT MAX(ID) FROM TACO",Long.class);
        }
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientOfTaco(String anIngredient,long aTacoId) {
        m_jdbc.update("INSERT INTO Taco_Ingredients (taco,ingredient) VALUES(?,?)",
                    aTacoId,anIngredient);
    }

}
