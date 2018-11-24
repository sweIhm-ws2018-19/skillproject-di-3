package verkocht.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * The class that represents a recipe.
 */
public class Recipe {
    private String name;
	private List<String> steps;
	private int numberOfPeople;
	private int cookingTime;
	private Category category;
	private Map<Ingredient, Integer> ingredientAmounts = new HashMap<Ingredient, Integer> ();
	
	
	public Recipe(String name) {
	    this.name = name;    }

    /**
	 * 
	 * @return
	 */
	public Map<Ingredient, Integer> getIngredients() {
		return this.ingredientAmounts;
	}
	
	/**
	 * 
	 * @return
	 */
	public Recipe changeIngredientAmounts() {
		return null;
	}

    public String getName() {
        return name;
    }


    
	
}
