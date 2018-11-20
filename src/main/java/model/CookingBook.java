package main.java.model;

import java.util.List;

/*
 * The cooking book that holds all recipes and categories.
 */
public class CookingBook {
	private List<Recipe> favorites;
	private List<Recipe> allRecipes;
	private List<Ingredient> allIngredients;
	
	public CookingBook() {
		// Schnitzel
		Recipe meatRecipe = new Recipe();
		meatRecipe.getIngredients().put(new Ingredient("Fleisch", Unit.GRAMM), 200);
		meatRecipe.getIngredients().put(new Ingredient("Eier", Unit.STUECK), 1);
		meatRecipe.getIngredients().put(new Ingredient("Mehl", Unit.GRAMM), 100);
		
		// Erdbeermilchshake
		Recipe vegetarianRecipe = new Recipe();
		vegetarianRecipe.getIngredients().put(new Ingredient("Erdbeeren", Unit.GRAMM), 100);
		vegetarianRecipe.getIngredients().put(new Ingredient("Milch", Unit.MILLILITER), 500);
		vegetarianRecipe.getIngredients().put(new Ingredient("Zucker", Unit.GRAMM), 50);
		
		// Nudeln mit Tomatensauce
		Recipe veganRecipe = new Recipe();
		veganRecipe.getIngredients().put(new Ingredient("Nudeln", Unit.GRAMM), 200);
		veganRecipe.getIngredients().put(new Ingredient("Salz", Unit.BRISE), 1);
		veganRecipe.getIngredients().put(new Ingredient("Tomatensauce", Unit.GRAMM), 100);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Recipe findByName(String name) {
		return null;
	}
	
	/**
	 * 
	 * @param category
	 * @return
	 */
	public Recipe findByCategory(Category category) {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Recipe findByFavorite(String favorite) {
		return null;
	}
}
