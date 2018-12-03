package verkocht.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import verkocht.handlers.TellRecipeStepsIntentHandler;

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
    private static int stepsCounter;
    private static Recipe recipeToRead;

	
	public Recipe(String name, Category category, int nrOfPeople, int coockingTime) {
	    this.name = name;
	    this.category = category;
	    this.cookingTime = coockingTime;
	    this.numberOfPeople = nrOfPeople;
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

    public static int getStepsCounter() {
        return stepsCounter;
    }

    public static void incStepsCounter() {
        Recipe.stepsCounter++;
    }
    

    public static Recipe getRecipeToRead() {
        return recipeToRead;
    }

    public static void setRecipeToRead(Recipe recipeToRead) {
        Recipe.recipeToRead = recipeToRead;
    }
}
