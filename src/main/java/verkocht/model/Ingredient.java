package verkocht.model;

/*
 * The class to represent an ingredient based on its unit.
 */
public class Ingredient {
	private String ingredient;
	private Unit unit;
	
	/**
	 * Ctor
	 * @param name
	 * @param unit
	 */
	public Ingredient(String name, Unit unit) {
		this.ingredient = name;
		this.unit = unit;
	}
}
