package verkocht.model;

/*
 * The class to represent an ingredient based on its unit.
 */
public class Ingredient {
	private String ingredientName;
	private Unit unitName;

	/**
	 * Ctor
	 * 
	 * @param name
	 * @param unit
	 */
	public Ingredient(String name, Unit unit) {
		this.ingredientName = name;
		this.unitName = unit;
	}

	public String getIngredient() {
		return this.ingredientName;
	}
	
	public Unit getUnit() {
		return this.unitName;
	}

}
