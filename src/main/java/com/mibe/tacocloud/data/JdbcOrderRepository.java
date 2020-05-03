package com.mibe.tacocloud.data;

import com.mibe.tacocloud.Order;
import com.mibe.tacocloud.Taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
interface JdbcOrderRepository implements OrderRepository {
    JdbcTemplate m_jdbc;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate aJdbcTemplate) {
        m_jdbc=aJdbcTemplate;
    }

    @Override
    public Order save(Order anOrder)
    {
        return anOrder;
    }
}
