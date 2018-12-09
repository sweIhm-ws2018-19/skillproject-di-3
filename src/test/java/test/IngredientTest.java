package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import verkocht.model.Ingredient;
import verkocht.model.Unit;

/**
 * Test class for the class Ingredient.
 */
public class IngredientTest {
    Ingredient ingredient;
    
    @Test
    public void testGetIngredient() {
        ingredient = new Ingredient("TestDerNichtSchmekt", Unit.SCHUSS);
        assertEquals("TestDerNichtSchmekt",ingredient.getIngredient());
    }
    
}
