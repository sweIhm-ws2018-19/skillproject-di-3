package main.java.verkocht.model;

/*
 * Class that represents all categories of the cooking book.
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
}