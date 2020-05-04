package com.mibe.tacocloud.data;

import com.mibe.tacocloud.Order;
import com.mibe.tacocloud.Taco;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
    
@Repository
public class JdbcOrderRepository implements OrderRepository {
    SimpleJdbcInsert orderInserter;
    SimpleJdbcInsert tacoOrderedInserter;
    ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate aJdbcHandler) {
        orderInserter=new SimpleJdbcInsert(aJdbcHandler)
            .withTableName("Taco_Order")
            .usingGeneratedKeyColumns("id");
        tacoOrderedInserter=new SimpleJdbcInsert(aJdbcHandler)
            .withTableName("Taco_Order_Tacos");
        this.objectMapper=new ObjectMapper();
    }

    @Override
    public Order save(Order anOrder)
    {
        anOrder.setPlacedAtToNow();
        long orderId=saveOrderDetails(anOrder);
        for(Long tacoId:anOrder.getTacos()) {
            saveOrderedTaco(tacoId,orderId);
        }
        return anOrder;
    }

    private long saveOrderDetails(Order anOrder) {
        @SuppressWarnings("unchecked")
        Map<String,Object> values=objectMapper.convertValue(anOrder,Map.class);
        values.put("placedAt",anOrder.getPlacedAt());
        long retId=orderInserter.executeAndReturnKey(values)
            .longValue();
        return retId;
    }

    private void saveOrderedTaco(Long aTacoId, Long anOrderId) {
        Map<String,Object> values=new HashMap<>();
        values.put("tacoOrder",anOrderId);
        values.put("taco",aTacoId);
        tacoOrderedInserter.execute(values);
    }
}
