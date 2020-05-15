package com.mibe.tacocloud.data;

import com.mibe.tacocloud.model.Ingredient;

public interface IngredientRepository {
	public Ingredient findOne(String id);
	public Iterable<Ingredient> findAll();
	public Ingredient save(Ingredient aNewIngredient);
}
