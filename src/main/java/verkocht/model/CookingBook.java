package verkocht.model;

import java.util.List;

/*
 * The cooking book that holds all recipes and categories.
 */
public class CookingBook {
	private List<Recipe> favorites;
	private static List<Recipe> allRecipes;
	private List<Ingredient> allIngredients;
	
	public CookingBook() {
		// Schnitzel
		Recipe meatRecipe = new Recipe();
		meatRecipe.getIngredients().put(new Ingredient("Fleisch", Unit.GRAMM), 200);
		meatRecipe.getIngredients().put(new Ingredient("Eier", Unit.STUECK), 1);
		meatRecipe.getIngredients().put(new Ingredient("Mehl", Unit.GRAMM), 100);
		allRecipes.add(meatRecipe);
		
		// Erdbeermilchshake
		Recipe vegetarianRecipe = new Recipe();
		vegetarianRecipe.getIngredients().put(new Ingredient("Erdbeeren", Unit.GRAMM), 100);
		vegetarianRecipe.getIngredients().put(new Ingredient("Milch", Unit.MILLILITER), 500);
		vegetarianRecipe.getIngredients().put(new Ingredient("Zucker", Unit.GRAMM), 50);
		allRecipes.add(vegetarianRecipe);
		
		// Nudeln mit Tomatensauce
		Recipe veganRecipe = new Recipe();
		veganRecipe.getIngredients().put(new Ingredient("Nudeln", Unit.GRAMM), 200);
		veganRecipe.getIngredients().put(new Ingredient("Salz", Unit.BRISE), 1);
		veganRecipe.getIngredients().put(new Ingredient("Tomatensauce", Unit.GRAMM), 100);
		allRecipes.add(veganRecipe);
		
		Recipe asiaWok = new Recipe();
		asiaWok.getIngredients().put(new Ingredient("Knochblauchzehe", Unit.STUECK), 1);
		asiaWok.getIngredients().put(new Ingredient("Ingwer", Unit.GRAMM), 20);
		asiaWok.getIngredients().put(new Ingredient("Tofu", Unit.GRAMM), 400);
		asiaWok.getIngredients().put(new Ingredient("Sojasauce", Unit.ESSLOEFFEL), 4);
		asiaWok.getIngredients().put(new Ingredient("Currypulver", Unit.TEELOEFFEL), 2);
		asiaWok.getIngredients().put(new Ingredient("Aubergine", Unit.STUECK), 1);
		asiaWok.getIngredients().put(new Ingredient("gelbe Paprika", Unit.STUECK), 1);
		asiaWok.getIngredients().put(new Ingredient("grüne Paprika", Unit.STUECK), 1);
		asiaWok.getIngredients().put(new Ingredient("Sonnenblumenoel", Unit.SCHUSS), 1);
		asiaWok.getIngredients().put(new Ingredient("Salz", Unit.BRISE), 1);
		asiaWok.getIngredients().put(new Ingredient("Spinat", Unit.GRAMM), 250);
		asiaWok.getIngredients().put(new Ingredient("Koriander", Unit.STUECK), 1);
		asiaWok.getIngredients().put(new Ingredient("Kokusmilch", Unit.MILLILITER), 300);
		asiaWok.getIngredients().put(new Ingredient("Woknudeln", Unit.GRAMM), 250);
		allRecipes.add(asiaWok);
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
	public static List<Recipe> findByCategory(Category category) {
		List<Recipe> a = null;
		switch (category) {
		case MEAT:
			for (int i = 0; i < allRecipes.size(); i++) {
				if(allRecipes.get(i).getCategory() == category.MEAT) {
				a.add(allRecipes.get(i));
				}
			}
			return a;
		case VEGETARIAN:
			for (int i = 0; i < allRecipes.size(); i++) {
				if(allRecipes.get(i).getCategory() == category.VEGETARIAN) {
				a.add(allRecipes.get(i));
				}
			}
			return a;
		case VEGAN:
			for (int i = 0; i < allRecipes.size(); i++) {
				if(allRecipes.get(i).getCategory() == category.VEGAN) {
				a.add(allRecipes.get(i));
				}
			}
			return a;
		default:
			throw new IllegalArgumentException("Unbekannte Kategorie: "+ category);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Recipe findByFavorite(String favorite) {
		return null;
	}
}
