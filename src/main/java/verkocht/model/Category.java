package verkocht.model;

/**
 *  Enum that represents all categories of the cooking book.
 *  @author Florian Uhrig
 */
public enum Category {
	MEAT("Fleisch"), VEGETARIAN("Vegetarisch"), VEGAN("Vegan");
	
	String name;
	
	/**
	 * Ctor to initialize the enum class.
	 * @param name The name of the category as string.
	 */
	Category(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the name of one category.
	 * @return The categories name as string.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Class to concat all category string to an answer.
	 * @return The list of categories as speech-like order.
	 */
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