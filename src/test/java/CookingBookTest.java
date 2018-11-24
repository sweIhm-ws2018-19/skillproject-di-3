import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import verkocht.model.*;

public class CookingBookTest {
	CookingBook a = new CookingBook();
	List<Recipe> b;
	@Test
	public void testFindByCategory() {
		b = (List<Recipe>) a.getAllRecipes().get(1);
		assertEquals(a.findByCategory(Category.MEAT),b);
	}
}
