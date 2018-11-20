package main.java.model;

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
	
	public List<Category> getCategories() {
		List<Category> returnList = new ArrayList<Category>();
		returnList.add(MEAT);
		returnList.add(VEGAN);
		returnList.add(VEGETARIAN);
		
		return returnList;
	}
}