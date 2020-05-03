package com.mibe.tacocloud;

// @Data
// @RequiredArgsConstructor
public class Ingredient {
	private final String m_id;
	private final String m_name;
	private final Type m_type;
	
	public Ingredient(String aId,String aName,Type aType) {
		m_id=aId;
		m_name=aName;
		m_type=aType;
	}
	
	public String id() { return m_id; }
	public String name() { return m_name; }
	public Type type() { return m_type; }

	public static enum Type {
		WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
	}
}
