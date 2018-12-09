package test;

import static org.junit.Assert.*;

import org.junit.Test;

import verkocht.model.*;

/**
 * Test class for the class Ingredient.
 */
public class RecipeTest {
	CookingBook cookingBook = new CookingBook();
	CookingBook cookingBook2 = new CookingBook();
    
    @Test
    public void testChangeIngredientAmount() {
    	Integer a = 100;
    	cookingBook.getAllRecipes().get(0).setNumberOfPeople(2);
    	cookingBook.getAllRecipes().get(0).changeIngredientAmounts();
    	Integer b = cookingBook.getAllRecipes().get(0).getIngredients().get(cookingBook.getIngredientByName("Mehl"));
    	assertEquals(a,b);
    	
    	a = 200;
    	cookingBook2.getAllRecipes().get(0).setNumberOfPeople(4);
    	cookingBook2.getAllRecipes().get(0).changeIngredientAmounts();
    	b = cookingBook2.getAllRecipes().get(0).getIngredients().get(cookingBook2.getIngredientByName("Mehl"));
    	assertEquals(a,b);
    }
}