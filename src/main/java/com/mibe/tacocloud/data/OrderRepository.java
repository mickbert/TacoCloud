package com.mibe.tacocloud.data;

import com.mibe.tacocloud.model.Order;

public interface OrderRepository {
    public Order save(Order anOrder);
}
