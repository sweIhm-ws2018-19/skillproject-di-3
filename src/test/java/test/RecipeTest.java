package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import verkocht.model.*;


public class RecipeTest {
    Recipe recipe = new Recipe("Pfannenkuchen", Category.VEGETARIAN, 2, 20);
    Ingredient mehl = new Ingredient("Mehl", Unit.GRAMM);
    Ingredient salz = new Ingredient("Salz", Unit.BRISE);
    Ingredient ei = new Ingredient("Ei", Unit.STUECK);
    Ingredient milch = new Ingredient("Milch", Unit.MILLILITER);
    Ingredient notAdded = new Ingredient("NotAdded", Unit.ESSLOEFFEL);
    
    @Before
    public void testInitiate() {
        CookingBook.initiateCookingBook();
    }

    @Test
    public void testConstructorAndGetter() {
        assertEquals("Should be Pfannenkuchen", "Pfannenkuchen", recipe.getName());
        assertEquals("Should be vegetarian", Category.VEGETARIAN, recipe.getCategory());
        assertEquals("Should be for two persons", 2, recipe.getNumberOfPeople());
        assertEquals("Should be done in 20 minutes", 20, recipe.getCookingTime());
    }
    
    @Test
    public void testIngredients() {
        Recipe.saveRecipe(recipe);
        recipe.addIngredient(mehl, 200);
        recipe.addIngredient(ei, 1);
        recipe.addIngredient(salz, 1);
        recipe.addIngredient(milch, 100);
        
        assertEquals("Should have four ingredients", 4, recipe.getIngredients().size());
        
        assertFalse(recipe.modifyByUnit("NotAdded", 4));
        assertTrue(recipe.modifyByUnit("Mehl",  new Integer(300)));
        assertEquals("Should have four ingredients", 4, recipe.getIngredients().size());
       
        assertEquals("Should be 300 now", 300, (int) recipe.getIngredients().get(mehl));

        // replacing already existing ingredient
        recipe.addIngredient(mehl, 100);
        assertEquals("Should be 100 now", 100, (int) recipe.getIngredients().get(mehl));
    }
    
        @Test
        public void testChangeIngredientAmount() { 
            Recipe recipe = CookingBook.getAllRecipes().get(0);
        	       	
        	Integer a = 25;
        	recipe.changeIngredientAmounts(2);
        	Integer b = CookingBook.getAllRecipes().get(0).getIngredients().get(CookingBook.getIngredientByName("Mehl"));
        	assertEquals(a,b);
        	
        	a = 50;
        	recipe.changeIngredientAmounts(4);
        	b = CookingBook.getAllRecipes().get(0).getIngredients().get(CookingBook.getIngredientByName("Mehl"));
        	assertEquals(a,b);
         	
        	Recipe recipe2 = new Recipe("Pfannenkuchen", Category.VEGETARIAN, 2, 20);
        	a = 1;
        	recipe2.getIngredients().put(CookingBook.getIngredientByName("Ei"), 0);
        	recipe2.changeIngredientAmounts(2);
        	b = recipe2.getIngredients().get(CookingBook.getIngredientByName("Ei"));
        	assertEquals(a,b);
        }
    }
