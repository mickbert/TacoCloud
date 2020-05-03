package com.mibe.tacocloud.data;

import com.mibe.tacocloud.Order;

interface OrderRepository {
    public Order save(Order anOrder);
}
