package verkocht.model;

/*
 * Enum for the units of the ingredients.
 */
public enum Unit {
	MILLILITER("Milliliter"), 
	GRAMM("Gramm"), 
	STUECK("Stueck"),
	TEELOEFFEL("Teeloeffel"), 
	ESSLOEFFEL("Essloeffel"), 
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