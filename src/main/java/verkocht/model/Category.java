package main.java.verkocht.model;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 */
public enum Category {
	MEAT("Fleisch"), VEGETARIAN("Vegetarisch"), VEGAN("Vegan");
	
	String name;
	
	Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	static public List<Category> getCategories() {
		List<Category> returnList = new ArrayList<Category>();
		returnList.add(MEAT);
		returnList.add(VEGETARIAN);
		returnList.add(VEGAN);
		
		return returnList;
	}
}