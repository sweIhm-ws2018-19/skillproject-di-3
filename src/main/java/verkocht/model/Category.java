package verkocht.model;

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
	
	public static String getCategories() {        
        StringBuilder categoryString = new StringBuilder();
        int length = values().length;
        
        for (int i = 0; i < length; i++) {
            categoryString.append(values()[i].getName());
            
            if (i == length - 2) {
                categoryString.append(" und ");
            } else if (i != length - 1) {
                categoryString.append(", ");
            }
        }
        
        return categoryString.toString();
	}
}