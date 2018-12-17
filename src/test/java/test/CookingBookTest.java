package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import verkocht.model.Category;
import verkocht.model.CookingBook;
import verkocht.model.Recipe;

public class CookingBookTest {
    @Before
    public void testInitiate() {
        CookingBook.initiateCookingBook();
    }
    
    @Test
    public void testFindByName() {
        assertEquals("Incorrect value", CookingBook.getAllRecipes().get(0), CookingBook.findByName("schnitzel") );
        assertEquals("Incorrect value",CookingBook.getAllRecipes().get(1), CookingBook.findByName("milchshake"));
        assertEquals("Incorrect value", CookingBook.getAllRecipes().get(2), CookingBook.findByName("nudeln"));
        assertEquals("Incorrect value", null, CookingBook.findByName("kartoffeln"));   
    }
    
    @Test
    public void testGetIngredientByName() {
        assertEquals (CookingBook.getAllIngredients().get(0), CookingBook.getIngredientByName("Fleisch"));
        assertEquals (CookingBook.getAllIngredients().get(1), CookingBook.getIngredientByName("Ei"));
        assertEquals (null, CookingBook.getIngredientByName("Pute"));
    }
    
    @Test
    public void testConstructor() {
        assertEquals ("Zuerst wird wird das Mehl auf einen Teller gestreut.", CookingBook.findByName("schnitzel").getSteps().get(0));
        assertEquals ("Alle Zutaten mit einem Mixer vermischen.", CookingBook.findByName("milchshake").getSteps().get(1));
        assertEquals ("Die Nudeln abgiessen und mit der Sauce heiss servieren.", CookingBook.findByName("nudeln").getSteps().get(2));
    }
    
    @Test
    public void testFindByCategory() {
    	List<Recipe> listOfRecipes = new ArrayList<Recipe>();
    	listOfRecipes.add(cookingBook.getAllRecipes().get(0));
		assertEquals(cookingBook.findByCategory(Category.MEAT),listOfRecipes);
		listOfRecipes.clear();
    	listOfRecipes.add(cookingBook.getAllRecipes().get(1));
		assertEquals(cookingBook.findByCategory(Category.VEGETARIAN),listOfRecipes);
		listOfRecipes.clear();
    	listOfRecipes.add(cookingBook.getAllRecipes().get(2));
		assertEquals(cookingBook.findByCategory(Category.VEGAN),listOfRecipes);
    } 
}