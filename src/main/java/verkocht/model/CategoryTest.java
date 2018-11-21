package main.java.verkocht.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CategoryTest {
	
	@Test
	public void testGetName() {
		assertEquals("Fleisch", Category.MEAT.getName());
		assertEquals("Vegetarisch", Category.VEGETARIAN.getName());
		assertEquals("Vegan", Category.VEGAN.getName());
	}
}