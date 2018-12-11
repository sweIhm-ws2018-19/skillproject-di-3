package verkocht.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import verkocht.handlers.ModifyRecipeByUnitsIntentHandler;

/*
 * The class that represents a recipe.
 */
public class Recipe {
    private String name;
	private List<String> steps = new ArrayList<>();
	private int numberOfPeople;
	private int cookingTime;
	private Category category;
	private Map<Ingredient, Integer> ingredientAmounts = new HashMap<>();
    private static int stepsCounter;
    private static Recipe savedRecipe;

	

	public Category getCategory(){
		return category;
	}
	
	public Recipe(String name, Category category, int nrOfPeople, int coockingTime) {
	    this.name = name;
	    this.category = category;
	    this.cookingTime = coockingTime;
	    this.numberOfPeople = nrOfPeople;
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
		for (Ingredient key : this.ingredientAmounts.keySet() ) {
			Integer r = ingredientAmounts.get(key);
			ingredientAmounts.replace(key, r, r * this.numberOfPeople);
		}
		return null;
	}
		
	public void addIngredient(Ingredient ingredient, int value) {
		if (ingredientAmounts.containsKey(ingredient)) {
		    ingredientAmounts.replace(ingredient, value);
		} else {
		    ingredientAmounts.put(ingredient, value);
		}
	}
	
	public boolean modifyByUnit(String ingredient, int value) {
	    Set<Ingredient> keys = this.ingredientAmounts.keySet();
	    int originValue = 1;
	    boolean found = false;
	    
	    for (Ingredient key : keys) {
	        found = key.getIngredient().equals(ingredient);
	        
	        if (found) {
	            originValue = ingredientAmounts.get(key);
	            break;
	        }
	    }
	    
        if (found) {
            int difference = ((value * 100) / originValue);
            ingredientAmounts.forEach((k, v) -> {
                if (k.getIngredient() != ingredient) {
                    ingredientAmounts.replace(k, (v * difference) / 100);
                } else {
                    ingredientAmounts.replace(k, value);
                }
            });
        }
	    
	    return found;
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

    public static void setStepsCounter(int step) {
        Recipe.stepsCounter = step;
    }
    
    public static Recipe getSavedRecipe() {
        return savedRecipe;
    }

    public static void saveRecipe(Recipe recipeToSave) {
        Recipe.savedRecipe = recipeToSave;
        ModifyRecipeByUnitsIntentHandler.resetState();
    }
    
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }
    
    public void setNumberOfPeople(int numOfPpl) {
    	this.numberOfPeople = numOfPpl;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }
    @Override
    public String toString() {
        String rec = "";
        return rec + this.getName();
        
    }
}
