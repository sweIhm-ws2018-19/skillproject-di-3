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
        System.out.println(CookingBook.getAllIngredients());

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
    	List<Recipe> listOfRecipes = CookingBook.getAllRecipes();
    	assertTrue(CookingBook.findByCategory(Category.MEAT).contains(listOfRecipes.get(0)));
    	assertTrue(CookingBook.findByCategory(Category.VEGETARIAN).contains(listOfRecipes.get(1)));
    	assertTrue(CookingBook.findByCategory(Category.VEGAN).contains(listOfRecipes.get(2)));
    }
    
}