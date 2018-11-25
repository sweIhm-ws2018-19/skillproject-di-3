package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


import verkocht.model.CookingBook;

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
    public void getIngredientByName() {
        System.out.println(cookingBook.getAllIngredients());

//        assertEquals(cookingBook.findByName("schnitzel"), cookingBook.getAllRecipes().get(0));
//        assertEquals(cookingBook.findByName("milchshake"), cookingBook.getAllRecipes().get(1));
//        assertEquals(cookingBook.findByName("nudelnMitTomatensoße"), cookingBook.getAllRecipes().get(2));
// 
        
    }
}
