package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import verkocht.model.*;

public class CookingBookTest {
    CookingBook cookingBook = new CookingBook();

    @Test
    public void testFindByName() {
        System.out.println(cookingBook.getAllIngredients());

        assertEquals("Incorrect value", cookingBook.getAllRecipes().get(0), cookingBook.findByName("schnitzel") );
        assertEquals("Incorrect value",cookingBook.getAllRecipes().get(1), cookingBook.findByName("milchshake"));
        assertEquals("Incorrect value", cookingBook.getAllRecipes().get(2), cookingBook.findByName("nudelnMitTomatensoße"));
        assertEquals("Incorrect value", null, cookingBook.findByName("nudeln"));   
    }
    
    @Test
    public void testGetIngredientByName() {
        assertEquals (cookingBook.getAllIngredients().get(0), cookingBook.getIngredientByName("Fleisch"));
        assertEquals (cookingBook.getAllIngredients().get(1), cookingBook.getIngredientByName("Ei"));
        assertEquals (null, cookingBook.getIngredientByName("Pute"));
            }
    @Test
    public void testConstructor() {
        assertEquals ("Zuerst wird wird das Mehl auf einen Teller gestreut.", cookingBook.findByName("schnitzel").getSteps().get(0));
        assertEquals ("Alle Zutaten mit einem Mixer vermischen.", cookingBook.findByName("milchshake").getSteps().get(1));
        assertEquals ("Die Nudeln abgießen und mit der Sauce heiß servieren.", cookingBook.findByName("nudelnMitTomatensoße").getSteps().get(2));
    }
    
    @Test
    public void testFindByCategory() {
    	List<Recipe> b = new ArrayList<Recipe>();
    	b.add(cookingBook.getAllRecipes().get(0));
		assertEquals(cookingBook.findByCategory(Category.MEAT),b);
		b.clear();
    	b.add(cookingBook.getAllRecipes().get(1));
		assertEquals(cookingBook.findByCategory(Category.VEGETARIAN),b);
		b.clear();
    	b.add(cookingBook.getAllRecipes().get(2));
		assertEquals(cookingBook.findByCategory(Category.VEGAN),b);
    }
    
}