import static org.junit.Assert.assertEquals;

import org.junit.Test;

import verkocht.model.Category;


public class CategoryTest {
	@Test
	public void testGetName() {
		assertEquals("Fleisch", Category.MEAT.getName());
		assertEquals("Vegetarisch", Category.VEGETARIAN.getName());
		assertEquals("Vegan", Category.VEGAN.getName());
	}
}