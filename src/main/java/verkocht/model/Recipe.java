package main.java.verkocht.model;

import java.util.List;
import java.util.Map;

/*
 * The class that represents a recipe.
 */
public class Recipe {
	private List<String> steps;
	private int numberOfPeople;
	private int cookingTime;
	private Category category;
	private Map<Ingredient, Integer> ingredientAmounts;
	
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
}
