package main.java.model;

/*
 * Enum for the units of the ingredients.
 */
public enum Unit {
	MILLILITER("Milliliter"), 
	GRAMM("Gramm"), 
	STUECK("Stük"),
	TEELOEFFEL("Teelöffel"), 
	ESSLOEFFEL("Esslöffel"), 
	BRISE("Brise"), 
	SCHUSS("Schuss"),
	TASSE("Tasse");
	
	String unit;
	
	Unit(String unit) {
		this.unit = unit; 
	}
	
	public String getUnit() {
		return this.unit;
	}
}