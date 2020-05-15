package com.mibe.tacocloud.model;

// @Data
// @RequiredArgsConstructor
public class Ingredient {
	private final String id;
	private final String name;
	private final Type type;
	
	public Ingredient(String aId,String aName,Type aType) {
		id=aId;
		name=aName;
		type=aType;
	}
	
	public String id() { return id; }
	public String name() { return name; }
	public Type type() { return type; }

	public static enum Type {
		WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
	}
}
