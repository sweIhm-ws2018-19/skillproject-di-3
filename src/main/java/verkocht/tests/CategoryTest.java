package main.java.verkocht.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.verkocht.model.Category;

public class CategoryTest {
	
	@Test
	public void testGetName() {
		assertEquals("Fleisch", Category.MEAT.getName());
		assertEquals("Vegetarisch", Category.VEGETARIAN.getName());
		assertEquals("Vegan", Category.VEGAN.getName());
	}
}