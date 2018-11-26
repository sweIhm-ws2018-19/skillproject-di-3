package verkocht.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * The class that represents a recipe.
 */
public class Recipe {
    private String name;
	private List<String> steps = new ArrayList <String>();
	private int numberOfPeople;
	private int cookingTime;
	private Category category;
	private Map<Ingredient, Integer> ingredientAmounts = new HashMap<Ingredient, Integer> ();
	
	public Recipe(String name, Category category) {
	    this.name = name;
	    this.category = category;
	    }
	
	public Category getCategory() {
		return category;
	}

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

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

	


    
	
}
