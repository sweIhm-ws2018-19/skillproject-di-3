package verkocht.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import verkocht.handlers.ModifyRecipeByUnitsIntentHandler;
import verkocht.handlers.TellRecipeStepsIntentHandler;

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
    public void addIngredient(Ingredient ingredient, int value) {
        if (ingredientAmounts.containsKey(ingredient)) {
            ingredientAmounts.replace(ingredient, value);
        } else {
            ingredientAmounts.put(ingredient, value);
        }
    }

    public boolean modifyByUnit(String ingredient, int value) {
        Recipe modifiedRecipe = Recipe.savedRecipe;
        Set<Ingredient> keys = modifiedRecipe.ingredientAmounts.keySet();
        int originValue = 1;
        boolean found = false;

        for (Ingredient key : keys) {
            found = key.getIngredient().equals(ingredient);

            if (found) {
                originValue = modifiedRecipe.ingredientAmounts.get(key);
                break;
            }
        }

        if (found) {
            int difference = ((value * 100) / originValue);
            modifiedRecipe.ingredientAmounts.forEach((k, v) -> {
                if (k.getIngredient() != ingredient) {
                    modifiedRecipe.ingredientAmounts.replace(k, (v * difference) / 100);
                } else {
                    modifiedRecipe.ingredientAmounts.replace(k, value);
                }
            });

            Recipe.saveRecipe(modifiedRecipe);
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
        ModifyRecipeByUnitsIntentHandler.resetState();
        TellRecipeStepsIntentHandler.resetCnt();
        Recipe.savedRecipe = recipeToSave;
    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
